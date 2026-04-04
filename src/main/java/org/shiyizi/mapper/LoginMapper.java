package org.shiyizi.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.shiyizi.pojo.Deity;

@Mapper
public interface LoginMapper {

    @Select("select * from deity where username=#{username} and password=#{password}")
    public Deity login(Deity deity);
}
