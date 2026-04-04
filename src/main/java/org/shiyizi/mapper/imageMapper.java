package org.shiyizi.mapper;

import org.apache.ibatis.annotations.*;
import org.shiyizi.pojo.image;

import java.util.List;

@Mapper
public interface imageMapper {

    @Select("SELECT id, file_url AS fileUrl, create_time AS createTime FROM image")
    public List<image> selectAll();

    @Select("SELECT id, file_url AS fileUrl, create_time AS createTime FROM image WHERE id=#{id}")
    public image selectById(Integer id);

    @Insert("INSERT INTO image (file_url, create_time) VALUES (#{fileUrl}, #{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insert(image image);

    @Delete("DELETE FROM image WHERE id=#{id}")
    public int deleteById(Integer id);

    @Update("UPDATE image SET file_url=#{fileUrl}, create_time=#{createTime} WHERE id=#{id}")
    public int update(image image);
}
