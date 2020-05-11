package com.tensquare.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.article.pojo.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{

    /**
     * 更新文章审核状态
     * 在增删改的操作时必须使用@Modifying注解
     * @param articleId
     */
    @Modifying
    @Query(value = "UPDATE tb_article SET state = 1 WHERE id = ?", nativeQuery = true)
    public void updateArticleState(String articleId);


    /**
     * 更新文章点赞数
     * 在增删改的操作时必须使用@Modifying注解
     * @param articleId
     */
    @Modifying
    @Query(value = "UPDATE tb_article SET thumbup = thumbup + 1 WHERE id = 1", nativeQuery = true)
    public void updateArticleThumbup(String articleId);
}
