package com.kezhang.tliasbackend.controller;

import com.aliyuncs.exceptions.ClientException;
import com.kezhang.aliyunossoperator.AliyunOssUtil;
import com.kezhang.tliasbackend.annotation.OperationLog;
import com.kezhang.tliasbackend.common.Result;
import com.kezhang.tliasbackend.utils.LocalDriveUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileManageController {
    // 本地存储方法
    private final LocalDriveUtil localDriveUtil;
    private final AliyunOssUtil aliyunOssUtil;
    @Autowired
    public FileManageController(AliyunOssUtil aliyunOssUtil, LocalDriveUtil localDriveUtil, LocalDriveUtil localDriveUtil1) {
        this.aliyunOssUtil = aliyunOssUtil;
        this.localDriveUtil = localDriveUtil1;
    }

    /*
    * 处理图片上传的接口
    * @param file 上传的文件
    * @return 返回上传成功后的文件访问路径
    * */
    @PostMapping("/upload/employee/image/local")
    @Operation(description = "Upload employee image to local storage")
    @OperationLog
    public Result<?> employeeImageLocalUpload(@RequestParam("file") MultipartFile file) throws IOException {
        // 获取当前日期并格式化为 "yyyy/MM" 形式,并且加上业务逻辑特定的路径前缀
        String servicePath = "employee/image/" +
                LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM")) + "/";
        String url = localDriveUtil.saveFile(file, servicePath);
        log.info("Image uploaded successfully, URL: {}", url);
        return Result.success(url);
    }
    /*
    * 处理图片本地删除的接口（前端直接调用）
    * 解释：
    *  1. 前端在添加员工的dialog中，选择上传头像之后，立即上传到阿里云OSS，
    *  2. 如果此时放弃添加员工，则需要删除阿里云OSS上已经上传的头像，
    * */
    @DeleteMapping("/delete/employee/image/local")
    @Operation(description = "Delete employee image from local storage")
    @OperationLog
    public Result<?> employeeImageLocalDelete(@RequestBody String fileURL){
        // 直接调用本地存储方法的删除方法
        localDriveUtil.deleteFile(fileURL);
        log.info("Image deleted successfully from local storage, URL: {}", fileURL);
        return Result.success(null);
    }

    // 阿里云OSS存储方法
    /*
    * Handles employee's image upload.
    * @param file The image file to be uploaded.
    * @return Result containing the URL of the uploaded image.
    * */
    @PostMapping("/upload/employee/image/oss")
    @Operation(description = "Upload employee image to Aliyun OSS")
    @OperationLog
    public Result<?> employeeImageUpload(@RequestParam("file") MultipartFile file) throws IOException, ClientException {
        // 获取当前日期并格式化为 "yyyy/MM" 形式,并且加上业务逻辑特定的路径前缀
        String servicePath = "employee/image/" +
                LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM")) + "/";
        String url = aliyunOssUtil.uploadFile(file, servicePath);
        log.info("Employee image uploaded successfully, URL: {}", url);
        return Result.success(url);
    }
    /*
    * 处理图片阿里云OSS删除的接口（前端直接调用）
    * 解释：
    *  1. 前端在添加员工的dialog中，选择上传头像之后，立即上传到阿里云OSS，
    *  2. 如果此时放弃添加员工，则需要删除阿里云OSS上已经上传的头像，
    * */
    @DeleteMapping("/delete/employee/image/oss")
    @Operation(description = "Delete employee image from Aliyun OSS")
    @OperationLog
    public Result<?> employeeImageDelete(@RequestParam("fileURL") String fileURL) throws ClientException {
        aliyunOssUtil.deleteFile(fileURL);
        log.info("Employee image deleted successfully from Aliyun OSS, URL: {}", fileURL);
        return Result.success(null);
    }
}
