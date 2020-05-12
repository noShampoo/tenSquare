package com.tensquare.search.controller;

import com.tensquare.search.service.ArticleService;
import com.tensquare.search.pojo.Article;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public Result save(@RequestBody Article article) {
        articleService.save(article);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @GetMapping("/{key}/{page}/{size}")
    public Result findByKey(@PathVariable("key") String key,
                            @PathVariable("page") int page,
                            @PathVariable("size") int size) {
        Page<Article> articlePage = articleService.findByKey(key, page, size);
        return new Result(true, StatusCode.OK, "查询成功",
                new PageResult<Article>(articlePage.getTotalElements(), articlePage.getContent()));
    }

}
