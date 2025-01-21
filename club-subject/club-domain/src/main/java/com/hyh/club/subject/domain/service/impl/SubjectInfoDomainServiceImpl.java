package com.hyh.club.subject.domain.service.impl;

import com.hyh.club.subject.common.enums.IsDeletedFlagEnum;
import com.hyh.club.subject.domain.convert.SubjectAnswerConvert;
import com.hyh.club.subject.domain.convert.SubjectInfoConvert;
import com.hyh.club.subject.domain.entity.SubjectInfoBO;
import com.hyh.club.subject.domain.factoryHandler.subject.SubjectInfoFactory;
import com.hyh.club.subject.domain.factoryHandler.subject.SubjectTypeHandler;
import com.hyh.club.subject.domain.service.SubjectInfoDomainService;
import com.hyh.club.subject.infra.basic.entity.SubjectAnswer;
import com.hyh.club.subject.infra.basic.entity.SubjectInfo;
import com.hyh.club.subject.infra.basic.entity.SubjectMapping;
import com.hyh.club.subject.infra.basic.service.SubjectInfoService;
import com.hyh.club.subject.infra.basic.service.SubjectMappingService;
import org.springframework.boot.logging.java.JavaLoggingSystem;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectInfoDomainServiceImpl implements SubjectInfoDomainService {
    @Resource
    private SubjectInfoService subjectInfoService;
    @Resource
    private SubjectInfoFactory subjectInfoFactory;
    @Resource
    private SubjectMappingService subjectMappingService;
    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        SubjectInfo subjectInfo = SubjectInfoConvert.INSTANCE.convertBoToSubjectInfo(subjectInfoBO);
        subjectInfo.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        //插入主表
        subjectInfoService.insert(subjectInfo);
        subjectInfoBO.setId(subjectInfo.getId());
        //插入题目类型表，使用工厂策略
        SubjectTypeHandler infoFactoryHandler = subjectInfoFactory.getHandler(subjectInfoBO.getSubjectType());
        if(infoFactoryHandler == null){
            return;
        }
        infoFactoryHandler.add(subjectInfoBO);
        List<SubjectMapping> subjectMappingList = new ArrayList<>();
        List<Integer> categoryIds = subjectInfoBO.getCategoryIds();
        List<Integer> labelIds = subjectInfoBO.getLabelIds();
        categoryIds.forEach(categoryId->{
            labelIds.forEach(labelId->{
                SubjectMapping subjectMapping = new SubjectMapping();
                subjectMapping.setSubjectId(subjectInfo.getId());
                subjectMapping.setCategoryId(Long.valueOf(categoryId));
                subjectMapping.setLabelId(String.valueOf(labelId));
                subjectMapping.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
                subjectMappingList.add(subjectMapping);
            });
        });
        subjectMappingService.batchInsert(subjectMappingList);

    }
}
