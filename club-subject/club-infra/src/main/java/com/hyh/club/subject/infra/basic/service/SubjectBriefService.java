package com.hyh.club.subject.infra.basic.service;

import com.hyh.club.subject.infra.basic.entity.SubjectBrief;

import java.util.List;


/**
 * 简答题(SubjectBrief)表服务接口
 *
 * @author Condix
 * @since 2025-01-21 12:18:45
 */
public interface SubjectBriefService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectBrief queryById(Long id);

    /**
     * 新增数据
     *
     * @param subjectBrief 实例对象
     * @return 实例对象
     */
    SubjectBrief insert(SubjectBrief subjectBrief);

    /**
     * 修改数据
     *
     * @param subjectBrief 实例对象
     * @return 实例对象
     */
    SubjectBrief update(SubjectBrief subjectBrief);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    void batchInsert(List<SubjectBrief> subjectBriefList);
}
