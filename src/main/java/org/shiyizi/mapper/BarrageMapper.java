package org.shiyizi.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.shiyizi.pojo.Barrage;

import java.util.List;

@Mapper

public interface BarrageMapper {
    @Select("select id, content, username, create_time from barrage order by id desc")
    List<Barrage> list();

    @Insert("insert into barrage(content, username, create_time) values(#{content}, #{username}, #{createTime})")
    int insert(Barrage barrage);
}