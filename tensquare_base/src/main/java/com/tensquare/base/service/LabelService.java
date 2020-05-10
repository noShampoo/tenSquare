package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * 标签业务类
 */
@Service
@Transactional
public class LabelService {

    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询所有标签数据
     * @return
     */
    public List<Label> findAll() {
        return labelDao.findAll();
    }

    /**
     * 根据ID查询标签数据
     */
    public Label findById(String id) {
        return labelDao.findById(id).get();
    }

    /**
     * 增加标签数据
     */
    public void add(Label label) {
        label.setId(idWorker.nextId() + "");//设置id
        labelDao.save(label);
    }

    /**
     * 修改标签数据
     */
    public void update(Label label) {
        labelDao.save(label);
    }

    /**
     * 根据ID删除标签
     */
    public void deleteById(String id) {
        labelDao.deleteById(id);
    }

    /**
     * 条件查询
     * @param label
     * @return
     */
    public List<Label> findSearch(Label label) {
        return labelDao.findAll(new Specification<Label>() {
            /**
             *
             * @param root 根对象，也就是说要把条件封装到哪个对象中。相当于where 类名 = label.getId
             * @param criteriaQuery 封装的是查询的一些关键字，比如group by或order by等等，一般不用
             * @param criteriaBuilder 封装条件对象,直接返回表示没有条件
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root,
                                         CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                //where labelbame like %labelname%
                if (label.getLabelname() != null && !"".equals(label.getLabelname())) {
                    Predicate predicate = criteriaBuilder.like(root.get("labelname").as(String.class),
                            "%" + label.getLabelname() + "%");
                    predicateList.add(predicate);
                }

                //where state = 'state'
                if (label.getState() != null && !"".equals(label.getState())) {
                    Predicate predicate = criteriaBuilder.equal(root.get("state").as(String.class),
                            label.getState());
                    predicateList.add(predicate);
                }
                Predicate[] predicates = new Predicate[predicateList.size()];
                predicates = predicateList.toArray(predicates);

                //where labelname like %labelname% and state = 'state'
                return criteriaBuilder.and(predicates);
            }
        });
    }

    /**
     * 分页查询
     * @param label
     * @param page
     * @param size
     * @return
     */
    public Page<Label> pageQuery(Label label, int page, int size) {
        Pageable pageRequest = PageRequest.of(page - 1, size);
        return labelDao.findAll(new Specification<Label>() {
            /**
             *
             * @param root 根对象，也就是说要把条件封装到哪个对象中。相当于where 类名 = label.getId
             * @param criteriaQuery 封装的是查询的一些关键字，比如group by或order by等等，一般不用
             * @param criteriaBuilder 封装条件对象,直接返回表示没有条件
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root,
                                         CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                //where labelbame like %labelname%
                if (label.getLabelname() != null && !"".equals(label.getLabelname())) {
                    Predicate predicate = criteriaBuilder.like(root.get("labelname").as(String.class),
                            "%" + label.getLabelname() + "%");
                    predicateList.add(predicate);
                }

                //where state = 'state'
                if (label.getState() != null && !"".equals(label.getState())) {
                    Predicate predicate = criteriaBuilder.equal(root.get("state").as(String.class),
                            label.getState());
                    predicateList.add(predicate);
                }
                Predicate[] predicates = new Predicate[predicateList.size()];
                predicates = predicateList.toArray(predicates);

                //where labelname like %labelname% and state = 'state'
                return criteriaBuilder.and(predicates);
            }
        }, pageRequest);
    }
}
