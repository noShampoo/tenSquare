package com.tensquare.search.service;

import com.tensquare.search.dao.ArticleDao;
import com.tensquare.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import util.IdWorker;

@Service
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 保存新数据
     * @param article
     */
    public void save(Article article) {
        article.setId(idWorker.nextId() + "");
        articleDao.save(article);
    }

    /**
     * 通过关键字或搜索字在标题和内容中查找
     * @param key
     * @param page
     * @param size
     * @return
     */
    public Page<Article> findByKey(String key, int page, int size) {
        return articleDao.findByTitleOrContentLike(key, key, PageRequest.of(page - 1, size));
    }
}
