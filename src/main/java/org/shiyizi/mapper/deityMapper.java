package org.shiyizi.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;
import org.shiyizi.pojo.Deity;

import java.util.List;

@Mapper
public interface deityMapper{

    @Select("select * from deity")
    public List<Deity> findAll();

    @Select("select * from deity where id=#{id}")
    public Deity findById(Integer id);

    @Insert("insert into deity values(null,#{name},#{username},#{password},#{gender},#{Clazz},#{birthday},#{phone})")
    public int add(Deity deity);

    @Delete("delete from deity where id=#{id}")
    public void deleteById(Integer id);

    @Update("update deity set name=#{name},username=#{username},password=#{password},gender=#{gender},Clazz=#{Clazz},birthday=#{birthday},phone=#{phone} where id=#{id}")
    public int update(Deity deity);

    @Update("update deity set avatar=#{avatar} where username=#{username}")
    public int updateAvatarByUsername(@Param("username") String username, @Param("avatar") String avatar);

    // 添加根据ID更新头像的方法（更安全）
    @Update("update deity set avatar=#{avatarUrl} where id=#{userId}")
    public int updateAvatarById(@Param("userId") Integer userId, @Param("avatarUrl") String avatarUrl);
}