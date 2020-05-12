package com.tensquare.spit.service;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SpitService {

    @Autowired
    private SpitDao spitDao;

    @Autowired
    private IdWorker idWorker;

    //使用mongo原生命令需要的
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 查询所有
     * @return
     */
    public List<Spit> findAll() {
        return spitDao.findAll();
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public Spit findById(String id) {
        return spitDao.findById(id).get();
    }

    /**
     * 保存新的吐槽
     * @param spit
     */
    public void save(Spit spit) {
        //对应吐槽的一些变量需要初始化
        spit.set_id( idWorker.nextId()+"" );
        spit.setPublishtime(new Date());//发布日期
        spit.setVisits(0);//浏览量
        spit.setShare(0);//分享数
        spit.setThumbup(0);//点赞数
        spit.setComment(0);//回复数
        spit.setState("1");//状态
        //增加了吐槽，且有父节点，也就是说增加的吐槽是一个子吐槽的时候就要让父吐槽的回复数加1
        if (spit.getParentid() != null && !"".equals(spit.getParentid())) {
            Query query = new Query();
            Update update = new Update();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
            update.inc("comment", 1);
            mongoTemplate.updateFirst(query, update, "spit");
        }
        spitDao.save(spit);
    }

    /**
     * 更新吐槽
     * @param spit
     */
    public void update(Spit spit) {
        spitDao.save(spit);
    }

    /**
     * 根据id删除吐槽
     * @param id
     */
    public void deleteById(String id) {
        spitDao.deleteById(id);
    }

    /**
     * 分页查询
     */
    public Page<Spit> findPageSpit(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return spitDao.findAll(pageRequest);
    }

    /**
     * 根据上级id查询吐槽数据并分页
     * @param parentid
     * @param page
     * @param size
     * @return
     */
    public Page<Spit> findByParentid(String parentid, int page, int size) {
        return spitDao.findByParentid(parentid, PageRequest.of(page - 1, size));
    }

    /**
     * 吐槽点赞
     */
    public void updateThumbup(String id) {
        //方法一，存在效率不高的问题
//        Spit spit = spitDao.findById(id).get();
//        spit.setThumbup(spit.getThumbup() == null ? 0 : spit.getThumbup() + 1);
//        spitDao.save(spit);

        //方法二、提高效率使用原生mongo命令更新:db.spit.update({"id":1}, {$inc:{"thumbup":NumberInt(1)}})
        Query query = new Query();
        Update update = new Update();
        query.addCriteria(Criteria.where("_id").is(id));
        update.inc("thumbup", 1);
        mongoTemplate.updateFirst(query, update, "spit");
    }

}
