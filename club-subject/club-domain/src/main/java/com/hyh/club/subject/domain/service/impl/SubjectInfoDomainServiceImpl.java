package com.hyh.club.subject.domain.service.impl;

import com.hyh.club.subject.common.enums.IsDeletedFlagEnum;
import com.hyh.club.subject.common.result.PageResult;
import com.hyh.club.subject.domain.convert.SubjectInfoConvert;
import com.hyh.club.subject.domain.entity.SubjectAnswerBO;
import com.hyh.club.subject.domain.entity.SubjectInfoBO;
import com.hyh.club.subject.domain.entity.SubjectOptionBo;
import com.hyh.club.subject.domain.factoryHandler.subject.SubjectInfoFactory;
import com.hyh.club.subject.domain.factoryHandler.subject.SubjectTypeHandler;
import com.hyh.club.subject.domain.service.SubjectInfoDomainService;
import com.hyh.club.subject.infra.basic.entity.SubjectInfo;
import com.hyh.club.subject.infra.basic.entity.SubjectLabel;
import com.hyh.club.subject.infra.basic.entity.SubjectMapping;
import com.hyh.club.subject.infra.basic.service.SubjectInfoService;
import com.hyh.club.subject.infra.basic.service.SubjectLabelService;
import com.hyh.club.subject.infra.basic.service.SubjectMappingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectInfoDomainServiceImpl implements SubjectInfoDomainService {
    @Resource
    private SubjectInfoService subjectInfoService;
    @Resource
    private SubjectInfoFactory subjectInfoFactory;
    @Resource
    private SubjectMappingService subjectMappingService;
    @Resource
    private SubjectLabelService subjectLabelService;

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        SubjectInfo subjectInfo = SubjectInfoConvert.INSTANCE.convertBoToSubjectInfo(subjectInfoBO);
        subjectInfo.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        //插入主表
        subjectInfoService.insert(subjectInfo);
        subjectInfoBO.setId(subjectInfo.getId());
        //插入题目类型表，使用工厂策略
        SubjectTypeHandler infoFactoryHandler = subjectInfoFactory.getHandler(subjectInfoBO.getSubjectType());
        if (infoFactoryHandler == null) {
            return;
        }
        infoFactoryHandler.add(subjectInfoBO);
        List<SubjectMapping> subjectMappingList = new ArrayList<>();
        List<Integer> categoryIds = subjectInfoBO.getCategoryIds();
        List<Integer> labelIds = subjectInfoBO.getLabelIds();
        categoryIds.forEach(categoryId -> {
            labelIds.forEach(labelId -> {
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

    @Override
    public PageResult<SubjectInfoBO> querySubjectList(SubjectInfoBO subjectInfoBO) {
        PageResult<SubjectInfoBO> pageResult = new PageResult<>();
        pageResult.setPageNo(subjectInfoBO.getPageNo());
        pageResult.setPageSize(subjectInfoBO.getPageSize());

        int start = (subjectInfoBO.getPageNo() - 1) * (subjectInfoBO.getPageSize());
        SubjectInfo subjectInfo = SubjectInfoConvert.INSTANCE.convertBoToSubjectInfo(subjectInfoBO);
        Integer subjectDifficult = subjectInfo.getSubjectDifficult();
        Integer categoryId = subjectInfo.getSubjectCategoryId();
        Integer labelId = subjectInfo.getSubjectLabelId();
        int count = subjectInfoService.querySubjectList(subjectInfo, categoryId, labelId);
        if (count == 0) {
            return pageResult;
        }
        List<SubjectInfo> subjectInfoList = subjectInfoService.queryPage(start, subjectInfo, subjectDifficult, categoryId, labelId, subjectInfoBO.getPageSize());
        List<SubjectInfoBO> subjectInfoBOS = SubjectInfoConvert.INSTANCE.convertSubjectInfoToBo(subjectInfoList);
        pageResult.setRecords(subjectInfoBOS);
        pageResult.setTotal(count);
        return pageResult;
    }

    @Override
    public SubjectInfoBO subjectInfoByCondition(SubjectInfoBO subjectInfoBO) {
        SubjectInfo subjectInfo = subjectInfoService.queryById(subjectInfoBO.getId());
        Integer subjectType = subjectInfo.getSubjectType();
        SubjectTypeHandler infoFactoryHandler = subjectInfoFactory.getHandler(subjectType);
        SubjectOptionBo queryOption = infoFactoryHandler.query(subjectInfoBO.getId().intValue());
        SubjectInfoBO bo  = SubjectInfoConvert.INSTANCE.convertOptionBoToInfo(queryOption,subjectInfo);
        List<String> labelNameList = new ArrayList<>();
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        subjectMapping.setSubjectId(subjectInfoBO.getId());
        List<SubjectMapping> subjectMappingList = subjectMappingService.queryListBySubjectId(subjectMapping);
        List<String> collect = subjectMappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        List<SubjectLabel> labelList = subjectLabelService.batchQueryById(collect);
        labelNameList = labelList.stream().map(SubjectLabel::getLabelName).collect(Collectors.toList());
        bo.setLabelNameList(labelNameList);
        bo.setOptionLists(queryOption.getOptionLists());
        return bo;
    }
}
