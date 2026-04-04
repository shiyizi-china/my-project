package org.shiyizi.service;

import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.jspecify.annotations.Nullable;
import org.shiyizi.pojo.image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface imageService {

    // 上传图片并保存记录
    public String uploadImage(MultipartFile file) throws Exception;

    // 查询所有图片
    public List<image> getAllImages();
}
