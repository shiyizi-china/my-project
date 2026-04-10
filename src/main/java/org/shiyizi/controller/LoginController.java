package org.shiyizi.controller;

import lombok.extern.log4j.Log4j2;
import org.shiyizi.pojo.Deity;
import org.shiyizi.pojo.LoginInfo;
import org.shiyizi.pojo.Result;
import org.shiyizi.service.LoginService;
import org.shiyizi.Util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Log4j2
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public Result login(@RequestBody Deity deity){
        if (deity == null) {
            log.warn("登录请求参数为空");
            return Result.error("请求参数不能为空");
        }
        
        LoginInfo loginInfo = loginService.login(deity);
        if (loginInfo != null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginInfo.getId());
            claims.put("username", loginInfo.getUsername());

            String token = JwtUtils.generateToken(claims);
            
            loginInfo.setToken(token);
            
            log.info("=== 登录成功 ===");
            log.info("用户 ID: {}", loginInfo.getId());
            log.info("用户名：{}", loginInfo.getUsername());
            log.info("生成的新 Token: {}", token);
            log.info("================");
            
            return Result.success(loginInfo);
        }
        log.info("登录失败:{}", deity);
        return Result.error("用户名或密码错误");
    }
    @GetMapping("/")
    public String health() {
        return "OK";  // 或者返回任何字符串，只要 HTTP 状态是 200
    }
}
