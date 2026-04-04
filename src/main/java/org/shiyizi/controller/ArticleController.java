package org.shiyizi.controller;


import org.shiyizi.pojo.Article;
import org.shiyizi.pojo.Result;
import org.shiyizi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
@CrossOrigin
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    // 发布文章
    @PostMapping
    public Result add(@RequestBody Article article) {
        boolean ok = articleService.add(article);
        return ok ? Result.success() : Result.error("发布失败");
    }

    // 文章列表
    @GetMapping
    public Result list() {
        return Result.success(articleService.list());
    }

    // 文章详情
    @GetMapping("/{id}")
    public Result get(@PathVariable Integer id) {
        return Result.success(articleService.getById(id));
    }
}