// org.shiyizi.service.UserServiceImpl.java
package org.shiyizi.service;

import lombok.extern.slf4j.Slf4j;
import org.shiyizi.mapper.UserMapper;
import org.shiyizi.pojo.Deity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Deity findById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("用户ID不能为空");
        }
        return userMapper.findById(id);
    }

    @Override
    public boolean updateAvatar(Integer userId, String avatarUrl) {
        if (userId == null || avatarUrl == null || avatarUrl.isEmpty()) {
            log.warn("更新头像参数无效 - userId: {}, avatarUrl: {}", userId, avatarUrl);
            return false;
        }

        try {
            int result = userMapper.updateAvatar(userId, avatarUrl);
            if (result > 0) {
                log.info("用户头像更新成功 - userId: {}, avatarUrl: {}", userId, avatarUrl);
                return true;
            } else {
                log.warn("用户头像更新失败，可能用户不存在 - userId: {}", userId);
                return false;
            }
        } catch (Exception e) {
            log.error("更新用户头像时发生异常 - userId: {}", userId, e);
            throw new RuntimeException("更新头像失败", e);
        }
    }
}