package org.shiyizi.controller;

import org.shiyizi.mapper.deityMapper;
import org.shiyizi.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private deityMapper deityMapper;

    @PostMapping("/avatar")
    public Result uploadAvatar(
            @RequestParam("file") MultipartFile file,
            @RequestHeader("username") String username
    ) {
        if (file.isEmpty()) {
            return Result.error("文件不能为空");
        }

        String fileName = System.currentTimeMillis() + ".jpg";
        String path = "uploads/";
        File dir = new File(path);
        if (!dir.exists()) dir.mkdirs();

        try {
            file.transferTo(new File(dir, fileName));
        } catch (IOException e) {
            return Result.error("上传失败");
        }

        String avatarUrl = "/uploads/" + fileName;
        deityMapper.updateAvatarByUsername(username, avatarUrl);

        // 返回包含avatarUrl的数据，而不是简单的字符串
        Map<String, String> resultData = new HashMap<>();
        resultData.put("avatarUrl", avatarUrl);
        return Result.success(resultData);
    }
}