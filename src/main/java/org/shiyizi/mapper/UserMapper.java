// org.shiyizi.mapper.UserMapper.java
package org.shiyizi.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.shiyizi.pojo.Deity;

@Mapper
public interface UserMapper {

    /**
     * 根据用户ID查询用户信息
     */
    Deity findById(@Param("id") Integer id);

    /**
     * 更新用户头像
     */
    @Update("UPDATE deity SET avatar = #{avatarUrl} WHERE id = #{userId}")
    int updateAvatar(@Param("userId") Integer userId, @Param("avatarUrl") String avatarUrl);
}