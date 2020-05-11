package com.tensquare.spit.service;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import java.util.List;

@Service
@Transactional
public class SpitService {

    @Autowired
    private SpitDao spitDao;

    @Autowired
    private IdWorker idWorker;

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
        spit.set_id(idWorker.nextId() + "");
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
        Spit spit = spitDao.findById(id).get();
        spit.setThumbup(spit.getThumbup() == null ? 0 : spit.getThumbup() + 1);
        spitDao.save(spit);
    }

}
