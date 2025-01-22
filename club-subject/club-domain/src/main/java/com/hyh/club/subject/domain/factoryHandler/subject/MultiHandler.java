package com.hyh.club.subject.domain.factoryHandler.subject;

import com.hyh.club.subject.common.enums.IsDeletedFlagEnum;
import com.hyh.club.subject.common.enums.SubjectTypeEnum;
import com.hyh.club.subject.domain.convert.SubjectMultiConvert;
import com.hyh.club.subject.domain.entity.SubjectInfoBO;
import com.hyh.club.subject.domain.entity.SubjectOptionBo;
import com.hyh.club.subject.infra.basic.entity.SubjectMultiple;
import com.hyh.club.subject.infra.basic.service.SubjectMultipleService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Component
public class MultiHandler implements SubjectTypeHandler{
    @Resource
    private SubjectMultipleService subjectMultipleService;
    @Override
    public SubjectTypeEnum getHandlerType() {
        return SubjectTypeEnum.MULTI;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        List<SubjectMultiple> subjectMultiples = new ArrayList<>();
        subjectInfoBO.getOptionLists().forEach(option -> {
            SubjectMultiple subjectMultiple = SubjectMultiConvert.INSTANCE.convert(option);
            subjectMultiple.setSubjectId(subjectInfoBO.getId());
            subjectMultiple.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
            subjectMultiples.add(subjectMultiple);
        });
        subjectMultipleService.batchInsert(subjectMultiples);
    }

    @Override
    public SubjectOptionBo query(int subjectId) {
        return null;
    }
}
