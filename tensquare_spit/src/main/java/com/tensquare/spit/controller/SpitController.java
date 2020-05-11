package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {

    @Autowired
    private SpitService spitService;

    /**
     * 查找所有吐槽
     * @return
     */
    @GetMapping
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", spitService.findAll());
    }

    /**
     * 根据id查找
     * @param spitId
     * @return
     */
    @GetMapping("/{spitId}")
    public Result findById(@PathVariable("spitId") String spitId) {
        return new Result(true, StatusCode.OK, "查询成功", spitService.findById(spitId));
    }

    /**
     * 增加吐槽
     * @param spit
     * @return
     */
    @PostMapping
    public Result addSpit(@RequestBody Spit spit) {
        spitService.save(spit);
        return new Result(true, StatusCode.OK, "保存成功");
    }

    /**
     * 更新吐槽
     * @param spit
     * @param spitId
     * @return
     */
    @PutMapping("/{spitId}")
    public Result updateSpit(@RequestBody Spit spit,
                             @PathVariable("spitId") String spitId) {
        spit.set_id(spitId);
        spitService.update(spit);
        return new Result(true, StatusCode.OK, "更新成功");
    }

    /**
     * 删除吐槽
     * @param spitId
     * @return
     */
    @DeleteMapping("{spitId}")
    public Result deleteSpitById(@PathVariable("spitId") String spitId) {
        spitService.deleteById(spitId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /**
     * 分页查询
     */
    @PostMapping("search/{page}/{size}")
    public Result findPageSpit(@PathVariable("page") int page,
                               @PathVariable("size") int size) {
        Page<Spit> spitPage = spitService.findPageSpit(page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Spit>(spitPage.getTotalElements(),
                spitPage.getContent()));
    }

    /**
     * 根据上级id查询吐槽数据并分页
     */
    @GetMapping("/comment/{parentid}/{page}/{size}")
    public Result findByParentid(@PathVariable("parentid") String parentid,
                                     @PathVariable("page") int page,
                                     @PathVariable("size") int size) {
        Page<Spit> spitPage = spitService.findPageSpit(page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Spit>(spitPage.getTotalElements(),
                spitPage.getContent()));
    }

    /**
     * 吐槽点赞
     */
    @PutMapping("/thumbup/{spitId}")
    public Result updateThumbup(@PathVariable("spitId") String spitId) {
        spitService.updateThumbup(spitId);
        return new Result(true, StatusCode.OK, "点赞成功");
    }
}
