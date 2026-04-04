package org.shiyizi.service;

import org.shiyizi.mapper.ArticleMapper;
import org.shiyizi.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public boolean add(Article article) {
        article.setCreateTime(LocalDateTime.now());
        return articleMapper.insert(article) > 0;
    }

    @Override
    public List<Article> list() {
        return articleMapper.list();
    }

    @Override
    public Article getById(Integer id) {
        return articleMapper.getById(id);
    }
}