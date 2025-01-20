package com.hyh.club.subject.domain.service.impl;

import com.hyh.club.subject.domain.convert.SubjectCategoryConvert;
import com.hyh.club.subject.domain.entity.SubjectCategoryBO;
import com.hyh.club.subject.domain.service.SubjectCategoryDomainService;
import com.hyh.club.subject.infra.basic.entity.SubjectCategory;
import com.hyh.club.subject.infra.basic.service.SubjectCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SubjectCategoryDomainServiceImpl implements SubjectCategoryDomainService {
    @Resource
    private SubjectCategoryService subjectCategoryService;
    @Override
    public void add(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConvert.INSTANCE.convertBoToCategory(subjectCategoryBO);
        subjectCategoryService.insert(subjectCategory);
    }

    @Override
    public List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConvert.INSTANCE.convertBoToCategory(subjectCategoryBO);
        List<SubjectCategory> subjectCategoryList = subjectCategoryService.queryCategory(subjectCategory);
        return SubjectCategoryConvert.INSTANCE.convertCategoryToBo(subjectCategoryList);
    }
}
