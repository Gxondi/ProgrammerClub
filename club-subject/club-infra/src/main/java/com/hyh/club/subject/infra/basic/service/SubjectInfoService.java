package com.hyh.club.subject.infra.basic.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hyh.club.subject.infra.basic.entity.SubjectInfo;

import java.util.List;


/**
 * 题目信息表(SubjectInfo)表服务接口
 *
 * @author Condix
 * @since 2025-01-21 12:16:19
 */
public interface SubjectInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectInfo queryById(Long id);

    /**
     * 新增数据
     *
     * @param subjectInfo 实例对象
     * @return 实例对象
     */
    SubjectInfo insert(SubjectInfo subjectInfo);

    /**
     * 修改数据
     *
     * @param subjectInfo 实例对象
     * @return 实例对象
     */
    SubjectInfo update(SubjectInfo subjectInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    int querySubjectList(SubjectInfo subjectInfo, Integer categoryId, Integer labelId);

    List<SubjectInfo> queryPage(Integer start, SubjectInfo subjectInfo, Integer subjectDifficult, Integer categoryId, Integer labelId, Integer pageSize);

    SubjectInfo subjectInfoByCondition(SubjectInfo subjectInfo);
}
