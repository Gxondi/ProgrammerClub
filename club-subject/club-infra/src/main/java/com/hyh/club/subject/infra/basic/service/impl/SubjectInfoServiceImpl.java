package com.hyh.club.subject.infra.basic.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hyh.club.subject.infra.basic.entity.SubjectInfo;
import com.hyh.club.subject.infra.basic.mapper.SubjectInfoDao;
import com.hyh.club.subject.infra.basic.service.SubjectInfoService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

/**
 * 题目信息表(SubjectInfo)表服务实现类
 *
 * @author Condix
 * @since 2025-01-21 12:16:19
 */
@Service("subjectInfoService")
public class SubjectInfoServiceImpl implements SubjectInfoService {
    @Resource
    private SubjectInfoDao subjectInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SubjectInfo queryById(Long id) {
        return this.subjectInfoDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param subjectInfo 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectInfo insert(SubjectInfo subjectInfo) {
        this.subjectInfoDao.insert(subjectInfo);
        return subjectInfo;
    }

    /**
     * 修改数据
     *
     * @param subjectInfo 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectInfo update(SubjectInfo subjectInfo) {
        this.subjectInfoDao.update(subjectInfo);
        return this.queryById(subjectInfo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.subjectInfoDao.deleteById(id) > 0;
    }

    @Override
    public int querySubjectList(SubjectInfo subjectInfo, Integer categoryId, Integer labelId) {

        return this.subjectInfoDao.queryByCondition(subjectInfo,categoryId,labelId);
    }

    @Override
    public List<SubjectInfo> queryPage(Integer start, SubjectInfo subjectInfo, Integer subjectDifficult, Integer categoryId, Integer labelId, Integer pageSize) {
        return  this.subjectInfoDao.queryPage(subjectInfo,start,subjectDifficult,labelId,categoryId,pageSize);
    }

    @Override
    public SubjectInfo subjectInfoByCondition(SubjectInfo subjectInfo) {

        return this.subjectInfoDao.subjectInfoByCondition(subjectInfo);
    }
}
