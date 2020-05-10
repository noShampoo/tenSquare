package com.tensquare.base.controller;


import com.sun.istack.internal.NotNull;
import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    /**
     * 查询全部标签
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
//        制造异常检验BaseExceptionHandler是否有用
//        int a = 1 / 0;
        return new Result(true, StatusCode.OK, "查询成功", labelService.findAll());
    }

    /**
     * 根据Id查询
     * @param labelId
     * @return
     */
    @RequestMapping(value = "/{labelId}", method = RequestMethod.GET)
    public Result findById(@PathVariable("labelId") String labelId) {
        return new Result(true, StatusCode.OK, "查询成功", labelService.findById(labelId));
    }

    /**
     * 增加标签
     * @param label
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Label label) {
        labelService.add(label);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /**
     * 更新标签
     * @param label
     * @return
     */
    @RequestMapping(value = "/{labelId}", method = RequestMethod.PUT)
    public Result update(@PathVariable("labelId") String labelId, @RequestBody Label label) {
        label.setId(labelId);
        labelService.update(label);
        return new Result(true, StatusCode.OK, "更新成功");
    }

    /**
     * 根据id删除标签元素
     * @param labelId
     * @return
     */
    @RequestMapping(value = "/{labelId}", method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable("labelId") String labelId) {
        labelService.deleteById(labelId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /**
     * 根据条件查询
     * @param label
     * @return
     */
    @PostMapping("/search")
    public Result findSearch(@RequestBody Label label) {
        List<Label> labelList = labelService.findSearch(label);
        return new Result(true, StatusCode.OK, "查询成功", labelList);
    }

    /**
     * 分页查询
     * @param label
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/search/{page}/{size}")
    public Result pageQuery(@RequestBody Label label,
                            @PathVariable("page") int page,
                            @PathVariable("size") int size) {
        Page<Label> labelPage = labelService.pageQuery(label, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Label>(labelPage.getTotalElements(),
                labelPage.getContent()));
    }
}
