package org.shiyizi.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.shiyizi.pojo.Article;

import java.util.List;

@Mapper
public interface ArticleMapper {

    @Insert("INSERT INTO article (title, content, username, create_time) VALUES (#{title}, #{content}, #{username}, #{createTime})")
    int insert(Article article);

    @Select("SELECT * FROM article")
    List<Article> list();

    @Select("SELECT * FROM article WHERE id = #{id}")
    Article getById(Integer id);
}