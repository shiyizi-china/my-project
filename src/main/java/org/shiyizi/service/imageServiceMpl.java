package org.shiyizi.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.shiyizi.Util.AliyunOSSOperator;
import org.shiyizi.mapper.imageMapper;
import org.shiyizi.pojo.image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class imageServiceMpl implements imageService{

    @Autowired
    private imageMapper imageMapper;
    
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @Override
    public String uploadImage(MultipartFile file) throws Exception {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("上传文件不能为空");
        }
        
        String url = aliyunOSSOperator.upload(file.getBytes(), Objects.requireNonNull(file.getOriginalFilename()));
        
        image image = new image();
        image.setFileUrl(url);
        image.setCreateTime(LocalDateTime.now());
        imageMapper.insert(image);
        
        log.info("图片上传成功：{}", url);
        return url;
    }

    @Override
    public List<image> getAllImages() {
        return imageMapper.selectAll();
    }
}

