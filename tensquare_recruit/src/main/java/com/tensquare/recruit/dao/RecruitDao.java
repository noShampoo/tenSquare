package com.tensquare.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.recruit.pojo.Recruit;

import java.util.List;

/**
 * 数据访问接口
 *
 * @author Administrator
 */
public interface RecruitDao extends JpaRepository<Recruit, String>, JpaSpecificationExecutor<Recruit> {

    /**
     * where state = ? order by createtime desc
     * @param state
     * @return
     */
    public List<Recruit> findTop5ByStateOrderByCreatetimeDesc(String state);

    /**
     * where state != ? order by createtime desc
     * @param state
     * @return
     */
    public List<Recruit> findTop5ByStateNotOrderByCreatetimeDesc(String state);
}
