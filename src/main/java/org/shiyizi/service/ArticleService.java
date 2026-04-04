package org.shiyizi.service;

import org.shiyizi.pojo.Article;

import java.util.List;

public interface ArticleService {
    boolean add(Article article);
    List<Article> list();
    Article getById(Integer id);
}