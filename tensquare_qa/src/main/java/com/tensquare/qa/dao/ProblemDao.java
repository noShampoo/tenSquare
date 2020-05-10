package com.tensquare.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{

    /**
     * 查询最新回复的问答列表
     * @Query注解的nativeQuery = true属性代表可以使用SQL语句
     * @return
     */
    @Query(value = "SELECT * FROM tb_problem, tb_pl WHERE tb_problem.id = tb_pl.problemid AND labelid = ? " +
            "ORDER BY tb_problem.replytime", nativeQuery = true)
    public Page<Problem> newProList(String labelId, Pageable pageable);

    /**
     * 查询热门回答的列表
     * @param labelId
     * @param pageable
     * @return
     */
    @Query(value = "SELECT * FROM tb_problem, tb_pl WHERE tb_problem.id = tb_pl.problemid AND labelid = ? " +
            "ORDER BY tb_problem.reply", nativeQuery = true)
    public Page<Problem> hotProList(String labelId, Pageable pageable);

    /**
     * 查询等待回答的列表
     * @param labelId
     * @param pageable
     * @return
     */
    @Query(value = "SELECT * FROM tb_problem, tb_pl WHERE tb_problem.id = tb_pl.problemid AND labelid = 1 " +
            "AND reply = 0 ORDER BY tb_problem.createtime", nativeQuery = true)
    public Page<Problem> waitProList(String labelId, Pageable pageable);
}
