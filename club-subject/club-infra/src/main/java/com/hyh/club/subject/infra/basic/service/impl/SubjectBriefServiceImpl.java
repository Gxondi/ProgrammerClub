package com.hyh.club.subject.infra.basic.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hyh.club.subject.infra.basic.entity.SubjectBrief;
import com.hyh.club.subject.infra.basic.mapper.SubjectBriefDao;
import com.hyh.club.subject.infra.basic.service.SubjectBriefService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

/**
 * 简答题(SubjectBrief)表服务实现类
 *
 * @author Condix
 * @since 2025-01-21 12:18:45
 */
@Service("subjectBriefService")
public class SubjectBriefServiceImpl implements SubjectBriefService {
    @Resource
    private SubjectBriefDao subjectBriefDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SubjectBrief queryById(Long id) {
        return this.subjectBriefDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param subjectBrief 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectBrief insert(SubjectBrief subjectBrief) {
        this.subjectBriefDao.insert(subjectBrief);
        return subjectBrief;
    }

    /**
     * 修改数据
     *
     * @param subjectBrief 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectBrief update(SubjectBrief subjectBrief) {
        this.subjectBriefDao.update(subjectBrief);
        return this.queryById(subjectBrief.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.subjectBriefDao.deleteById(id) > 0;
    }

    @Override
    public void batchInsert(List<SubjectBrief> subjectBriefList) {
        this.subjectBriefDao.insertBatch(subjectBriefList);
    }
}
