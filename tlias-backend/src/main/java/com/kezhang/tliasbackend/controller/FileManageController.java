package com.kezhang.tliasbackend.controller;

import com.aliyuncs.exceptions.ClientException;
import com.kezhang.tliasbackend.common.Result;
import com.kezhang.tliasbackend.utils.AliyunOssUtil;
import com.kezhang.tliasbackend.utils.LocalDriveUtil;
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
    public Result<?> employeeImageLocalUpload(@RequestParam("file") MultipartFile file) throws IOException {
        // 获取当前日期并格式化为 "yyyy/MM" 形式,并且加上业务逻辑特定的路径前缀
        String servicePath = "employee/image/" +
                LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM")) + "/";
        String url = localDriveUtil.saveFile(file, servicePath);
        log.info("Image uploaded successfully, URL: {}", url);
        return Result.success(url);
    }

    // 阿里云OSS存储方法
    /*
    * Handles employee's image upload.
    * @param file The image file to be uploaded.
    * @return Result containing the URL of the uploaded image.
    * */
    @PostMapping("/upload/employee/image/oss")
    public Result<?> employeeImageUpload(@RequestParam("file") MultipartFile file) throws IOException, ClientException {
        // 获取当前日期并格式化为 "yyyy/MM" 形式,并且加上业务逻辑特定的路径前缀
        String servicePath = "employee/image/" +
                LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM")) + "/";
        String url = aliyunOssUtil.uploadFile(file, servicePath);
        log.info("Employee image uploaded successfully, URL: {}", url);
        return Result.success(url);
    }
}
