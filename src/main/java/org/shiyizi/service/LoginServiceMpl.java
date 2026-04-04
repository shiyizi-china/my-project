package org.shiyizi.service;

import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.shiyizi.Util.AliyunOSSOperator;
import org.shiyizi.Util.JwtUtils;
import org.shiyizi.mapper.LoginMapper;
import org.shiyizi.pojo.Deity;
import org.shiyizi.pojo.LoginInfo;
import org.shiyizi.pojo.image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class LoginServiceMpl implements LoginService{

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public LoginInfo login(Deity deity) {
        Deity deity2 = loginMapper.login(deity);
        if (deity2 != null){
            log.info("登录:{}",deity);
            return new LoginInfo(deity2.getId(), deity2.getName(), "");
        }
        return null;
    }
}
