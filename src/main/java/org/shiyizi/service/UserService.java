// org.shiyizi.service.UserService.java
package org.shiyizi.service;

import org.shiyizi.pojo.Deity;

public interface UserService {

    /**
     * 根据ID查询用户
     */
    Deity findById(Integer id);

    /**
     * 更新用户头像
     */
    boolean updateAvatar(Integer userId, String avatarUrl);
}