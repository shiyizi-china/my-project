package org.shiyizi.service;

import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.shiyizi.pojo.Deity;
import org.shiyizi.pojo.LoginInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface LoginService {
    public LoginInfo login(Deity deity);
}
