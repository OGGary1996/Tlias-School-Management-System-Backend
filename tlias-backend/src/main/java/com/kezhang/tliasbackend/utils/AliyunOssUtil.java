package com.kezhang.tliasbackend.utils;

import com.aliyuncs.exceptions.ClientException;
import com.kezhang.tliasbackend.constant.AliyunOssProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.*;
import com.aliyun.oss.common.auth.*;
import com.aliyun.oss.common.comm.SignVersion;

import java.io.IOException;
import java.util.UUID;

@Slf4j // 使用Lombok的日志注解来简化日志记录
@Component // 这个注解将类标记为一个Spring组件，允许它被自动扫描和注入
public class AliyunOssUtil {
    // 阿里云OSS相关配置参数注入：采用@ConfigurationProperties的方式
//    @Value("${aliyun.oss.bucket-name}")
//    private String bucketName;
//
//    @Value("${aliyun.oss.dir}")
//    private String dir;
//
//    @Value("${aliyun.oss.endpoint}")
//    private String endpoint;
//
//    @Value("${aliyun.oss.region}")
//    private String region;
    private final AliyunOssProperties aliyunOssProperties;
    @Autowired
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
        log.info("Initializing OSSClient with endpoint: {}, region: {}", endpoint, region);
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        OSS ossClient = OSSClientBuilder.create()
                .endpoint(endpoint)
                .credentialsProvider(credentialsProvider)
                .region(region)
                .build();
        log.info("OSSClient initialized successfully, ossClient: {}", ossClient);
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
        log.info("File original filename: {}, extension name: {}", originalFilename, fileExtension);
        // 2. 生成一个新的文件名，使用UUID来避免文件名冲突
        String newFilename = UUID.randomUUID().toString() + fileExtension;
        // 3. 拼接上配置的dir
        String filePath = dir + servicePath + newFilename;
        log.info("File will be saved to: {}", filePath);
        // 4. 上传文件时，有两种方式：1.获取文件的输入流（适合大文件），2.获取文件的字节数组（可能导致内存溢出）
        OSS ossClient = getOSSClient();
        ossClient.putObject(bucketName,filePath,file.getInputStream());
        // 5. 关闭OSSClient实例
        if (ossClient != null) {
            ossClient.shutdown();
            log.info("OSSClient has been shut down successfully.");
        } else {
            log.warn("OSSClient was not initialized, no need to shut down.");
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
            log.info("File name to delete: {}", filePath);
        }else {
            log.error("File path does not start with the expected prefix: {}", fullEndpointPrefix);
        }
        // 创建OSSClient实例
        OSS ossClient = getOSSClient();
        // 删除文件
        ossClient.deleteObject(bucketName, filePath);
        log.info("File deleted successfully at path: {}", filePath);
    }
}
