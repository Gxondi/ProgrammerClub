package com.hyh.club.subject.infra.basic.service.impl;

import com.hyh.club.subject.infra.basic.entity.SubjectRadio;
import com.hyh.club.subject.infra.basic.mapper.SubjectRadioDao;
import com.hyh.club.subject.infra.basic.service.SubjectRadioService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

/**
 * 单选题信息表(SubjectRadio)表服务实现类
 *
 * @author Condix
 * @since 2025-01-21 12:18:05
 */
@Service("subjectRadioService")
public class SubjectRadioServiceImpl implements SubjectRadioService {
    @Resource
    private SubjectRadioDao subjectRadioDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SubjectRadio queryById(Long id) {
        return this.subjectRadioDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param subjectRadio 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectRadio insert(SubjectRadio subjectRadio) {
        this.subjectRadioDao.insert(subjectRadio);
        return subjectRadio;
    }

    /**
     * 修改数据
     *
     * @param subjectRadio 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectRadio update(SubjectRadio subjectRadio) {
        this.subjectRadioDao.update(subjectRadio);
        return this.queryById(subjectRadio.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.subjectRadioDao.deleteById(id) > 0;
    }

    @Override
    public void batchInsert(List<SubjectRadio> subjectRadioList) {
        this.subjectRadioDao.insertBatch(subjectRadioList);
    }

    @Override
    public List<SubjectRadio> queryBySubjectId(SubjectRadio subjectRadio) {

        return this.subjectRadioDao.queryBySubjectId(subjectRadio);
    }
}
