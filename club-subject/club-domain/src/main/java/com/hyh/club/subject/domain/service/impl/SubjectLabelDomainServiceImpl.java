package com.hyh.club.subject.domain.service.impl;

import com.hyh.club.subject.common.enums.IsDeletedFlagEnum;
import com.hyh.club.subject.domain.convert.SubjectLabelConvert;
import com.hyh.club.subject.domain.entity.SubjectLabelBO;
import com.hyh.club.subject.domain.service.SubjectLabelDomainService;
import com.hyh.club.subject.infra.basic.entity.SubjectLabel;
import com.hyh.club.subject.infra.basic.entity.SubjectMapping;
import com.hyh.club.subject.infra.basic.service.SubjectLabelService;
import com.hyh.club.subject.infra.basic.service.SubjectMappingService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectLabelDomainServiceImpl implements SubjectLabelDomainService {
    @Resource
    private SubjectLabelService subjectLabelService;
    @Resource
    private SubjectMappingService subjectMappingService;
    @Override
    public void add(SubjectLabelBO subjectLabelBO) {
        SubjectLabel subjectLabel = SubjectLabelConvert.INSTANCE.convertBoToCategory(subjectLabelBO);
        subjectLabel.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        subjectLabelService.insert(subjectLabel);
    }

    @Override
    public boolean update(SubjectLabelBO subjectLabelBO) {
        SubjectLabel subjectLabel = SubjectLabelConvert.INSTANCE.convertBoToCategory(subjectLabelBO);
        int count = subjectLabelService.update(subjectLabel);
        return count > 0;
    }

    @Override
    public boolean delete(SubjectLabelBO subjectLabelBO) {
        SubjectLabel subjectLabel = SubjectLabelConvert.INSTANCE.convertBoToCategory(subjectLabelBO);
        subjectLabel.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        int count = subjectLabelService.update(subjectLabel);
        return count > 0;
    }

    @Override
    public List<SubjectLabelBO> queryListByCategoryId(SubjectLabelBO subjectLabelBO) {
        String categoryId = subjectLabelBO.getCategoryId();
        // new一个mapping，添加查询条件
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setCategoryId(Long.valueOf(categoryId));
        subjectMapping.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        List<SubjectMapping> subjectMappings = subjectMappingService.queryLabelId(subjectMapping);
        List<String> collect = subjectMappings.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(collect)){
            return Collections.emptyList();
        }
        List<SubjectLabel> subjectLabelList = subjectLabelService.batchQueryByIds(collect);
        List<SubjectLabelBO> subjectLabelBOList = subjectLabelList.stream().map(subjectLabel -> {
            SubjectLabelBO labelBO = new SubjectLabelBO();
            labelBO.setCategoryId(subjectLabel.getCategoryId());
            labelBO.setLabelName(subjectLabel.getLabelName());
            labelBO.setId(subjectLabel.getId());
            return labelBO;
        }).collect(Collectors.toList());

        return subjectLabelBOList;
    }
}
