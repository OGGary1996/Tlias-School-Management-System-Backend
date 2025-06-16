package com.kezhang.tliasbackend.controller;

import com.aliyuncs.exceptions.ClientException;
import com.kezhang.tliasbackend.common.Result;
import com.kezhang.tliasbackend.utils.AliyunOssUtil;
import com.kezhang.tliasbackend.utils.LocalDriveStorage;
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
    private final LocalDriveStorage localDriveStorage;
    private final AliyunOssUtil aliyunOssUtil;
    @Autowired
    public FileManageController(AliyunOssUtil aliyunOssUtil, LocalDriveStorage localDriveStorage) {
        this.aliyunOssUtil = aliyunOssUtil;
        this.localDriveStorage = localDriveStorage;
    }

    /*
    * 处理图片上传的接口
    * @param file 上传的文件
    * @return 返回上传成功后的文件访问路径
    * */
    @PostMapping("/upload/employee/image/local")
    public Result<?> employeeImageLocalUpload(@RequestParam("file") MultipartFile file) throws IOException {
        // 获取当前日期并格式化为 "yyyy/MM" 形式,并且加上业务逻辑特定的路径前缀
        String servicePath = "employee/image/" +
                LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM")) + "/";
        String url = localDriveStorage.saveFile(file, servicePath);
        log.info("Image uploaded successfully, URL: {}", url);
        return Result.success(url);
    }
    /*
    * 处理文件的删除操作
    * @param filePath 要删除的文件路径
    * @return 返回删除操作的结果
    * */
    @DeleteMapping("/delete/employee/image/local/{fileUrl}")
    public Result<?> employeeImageLocalDelete(@PathVariable("fileUrl") String fileUrl){
        log.info("Deleting file at path: {}", fileUrl);
        // 调用本地存储方法删除文件
        localDriveStorage.deleteFile(fileUrl);
        log.info("File deleted successfully at path: {}", fileUrl);
        return Result.success(null);
    }


    // 阿里云OSS存储方法
    /*
    * Handles employee's image upload.
    * @param file The image file to be uploaded.
    * @return Result containing the URL of the uploaded image.
    * */
    @PostMapping("/upload/employee/image")
    public Result<?> employeeImageUpload(@RequestParam("file") MultipartFile file) throws IOException, ClientException {
        // 获取当前日期并格式化为 "yyyy/MM" 形式,并且加上业务逻辑特定的路径前缀
        String servicePath = "employee/image/" +
                LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM")) + "/";
        String url = aliyunOssUtil.uploadFile(file, servicePath);
        log.info("Employee image uploaded successfully, URL: {}", url);
        return Result.success(url);
    }
    /*
    * Handles deletion of employee's image.
    * @param filePath The path of the image file to be deleted.
    * @return Result indicating the success of the deletion operation.
    * */
    @DeleteMapping("/delete/employee/image/{fileUrl}")
    public Result<?> employeeImageDelete(@PathVariable("fileUrl") String fileUrl) throws ClientException {
        log.info("Deleting file at path: {}", fileUrl);
        // 调用阿里云OSS存储方法删除文件
        aliyunOssUtil.deleteFile(fileUrl);
        log.info("File deleted successfully at path: {}", fileUrl);
        return Result.success(null);
    }
}
