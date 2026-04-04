package org.shiyizi.controller;

import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.shiyizi.Util.AliyunOSSOperator;
import org.shiyizi.pojo.Result;
import org.shiyizi.pojo.image;
import org.shiyizi.service.imageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileFilter;
import java.io.IOException;
import java.text.FieldPosition;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/image")
@CrossOrigin
public class imageController {

    @Autowired
    private imageService imageService;

    // 上传：POST /image
    @PostMapping
    public Result upload(@RequestParam("file") MultipartFile file) throws Exception {
        String url = imageService.uploadImage(file);
        return Result.success(url); // 你自己的success方法
    }

    // 获取列表：GET /image
    @GetMapping
    public Result list() {
        return Result.success(imageService.getAllImages()); // 你自己的success
    }
}

