package org.shiyizi.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.shiyizi.Util.AliyunOSSOperator;
import org.shiyizi.Util.JwtUtils;
import org.shiyizi.pojo.Deity;
import org.shiyizi.pojo.Result;
import org.shiyizi.service.deityService; // 修改导入
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/user")
@Log4j2
public class UserController {

    @Autowired
    private deityService deityService; // 修改为deityService

    @Autowired
    private AliyunOSSOperator ossOperator;

    @PostMapping("/avatar")
    public Result uploadAvatar(@RequestParam("file") MultipartFile file,
                               HttpServletRequest request) {
        try {
            // 1. 从Token获取用户ID
            String token = request.getHeader("token");
            Map<String, Object> claims = JwtUtils.parseToken(token);
            Integer userId = (Integer) claims.get("id");

            // 2. 上传到OSS
            String avatarUrl = ossOperator.upload(file.getBytes(), file.getOriginalFilename());

            // 3. 更新用户头像 - 使用deityService
            boolean success = deityService.updateAvatar(userId, avatarUrl);

            if (success) {
                return Result.success(avatarUrl);
            } else {
                return Result.error("更新头像失败");
            }
        } catch (Exception e) {
            log.error("头像上传失败", e);
            return Result.error("上传失败: " + e.getMessage());
        }
    }

    @GetMapping("/me")
    public Result getCurrentUser(HttpServletRequest request) {
        try {
            String token = request.getHeader("token");
            Map<String, Object> claims = JwtUtils.parseToken(token);
            Integer userId = (Integer) claims.get("id");

            // 查询完整用户信息（包含头像）- 使用deityService
            Deity user = deityService.findById(userId);
            return Result.success(user);
        } catch (Exception e) {
            return Result.error("获取用户信息失败");
        }
    }
}