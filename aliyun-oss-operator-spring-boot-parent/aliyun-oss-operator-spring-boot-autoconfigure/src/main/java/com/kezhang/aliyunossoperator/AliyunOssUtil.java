package com.kezhang.aliyunossoperator;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyuncs.exceptions.ClientException;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public class AliyunOssUtil {
    // 阿里云OSS相关配置参数注入：采用@ConfigurationProperties的方式
    private final AliyunOssProperties aliyunOssProperties;
    public AliyunOssUtil(AliyunOssProperties aliyunOssProperties) {
        this.aliyunOssProperties = aliyunOssProperties;
    }


    /*
    * OSS实例化方法
    * @return 返回一个OSSClient实例
    * 流程：
    * 1. 从环境变量中获取访问凭证以及配置参数
    * 2. 创建OSSClient实例，显式声明使用V4签名算法
    * */
    private OSS getOSSClient() throws ClientException {
        String endpoint = aliyunOssProperties.getEndpoint();
        String region = aliyunOssProperties.getRegion();

        // 从环境变量中获取访问凭证，请先配置环境变量
        EnvironmentVariableCredentialsProvider credentialsProvider =
                CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        // 创建OSSClient实例
        // 当OSSClient实例不再使用时，调用shutdown方法以释放资源。
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        // 显式声明使用 V4 签名算法
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        OSS ossClient = OSSClientBuilder.create()
                .endpoint(endpoint)
                .credentialsProvider(credentialsProvider)
                .region(region)
                .build();
        return ossClient;
    }

    /*
    * 文件上传方法：
    * @param file 要上传的文件
    * @return 返回上传后的文件访问路径
    * 流程：
    * 1.获取文件的原始文件名和扩展名
    * 2.生成一个新的文件名，使用UUID来避免文件名冲突
    * 3. 拼接上配置的dir
    * 4. 上传文件时，有两种方式：1.获取文件的输入流（适合大文件），2.获取文件的字节数组（可能导致内存溢出）
    * 5. 关闭OSSClient实例
    * 6. 返回文件的访问路径
    * */
    public String uploadFile(MultipartFile file, String servicePath) throws IOException,ClientException {
        String bucketName = aliyunOssProperties.getBucketName();
        String dir = aliyunOssProperties.getDir();
        String endpoint = aliyunOssProperties.getEndpoint();

        // 1. 获取文件的原始文件名和扩展名
        String originalFilename = file.getOriginalFilename();
        assert originalFilename != null;
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        // 2. 生成一个新的文件名，使用UUID来避免文件名冲突
        String newFilename = UUID.randomUUID().toString() + fileExtension;
        // 3. 拼接上配置的dir
        String filePath = dir + servicePath + newFilename;
        // 4. 上传文件时，有两种方式：1.获取文件的输入流（适合大文件），2.获取文件的字节数组（可能导致内存溢出）
        OSS ossClient = getOSSClient();
        ossClient.putObject(bucketName,filePath,file.getInputStream());
        // 5. 关闭OSSClient实例
        if (ossClient != null) {
            ossClient.shutdown();
        }
        // 6. 返回文件的访问路径
        return "https://" + bucketName + "." + endpoint + "/" + filePath;
    }

    /*
    * 删除文件方法：
    * @param fileUrl 要删除的文件的访问路径
    * 流程：
    *  1. 获取bucketName和endpoint
    *  2. 检查文件路径是否以正确的前缀开始
    *  3. OSSClient实例中的删除方法需要的文件路径是从前缀之后开始的部分，删除前缀
    *  4. 调用OSSClient的deleteObject方法删除文件
    * */
    public void deleteFile(String fileUrl) throws ClientException{
        String bucketName = aliyunOssProperties.getBucketName();
        String endpoint = aliyunOssProperties.getEndpoint();
        String filePath = null;
        // 正确的前缀；以供检查
        String fullEndpointPrefix = "https://" + bucketName + "." + endpoint + "/";
        // 检查文件路径是否正常
        if (fileUrl.startsWith(fullEndpointPrefix)) {
            // 删除需要的文件路径是从前缀之后开始的部分
            filePath = fileUrl.substring(fullEndpointPrefix.length());
        }else {
            return; // 如果文件路径不正确，可能是OSS中没有存储这个文件，直接返回，不用删除。
        }
        // 创建OSSClient实例
        OSS ossClient = getOSSClient();
        // 删除文件
        ossClient.deleteObject(bucketName, filePath);
    }
}
