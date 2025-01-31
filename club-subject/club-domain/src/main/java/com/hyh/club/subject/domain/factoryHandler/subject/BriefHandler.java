package com.hyh.club.subject.domain.factoryHandler.subject;

import com.hyh.club.subject.common.enums.IsDeletedFlagEnum;
import com.hyh.club.subject.common.enums.SubjectTypeEnum;
import com.hyh.club.subject.domain.convert.SubjectBriefConvert;
import com.hyh.club.subject.domain.entity.SubjectInfoBO;
import com.hyh.club.subject.domain.entity.SubjectOptionBo;
import com.hyh.club.subject.infra.basic.entity.SubjectBrief;
import com.hyh.club.subject.infra.basic.service.SubjectBriefService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Component
public class BriefHandler implements SubjectTypeHandler{
    @Resource
    private SubjectBriefService subjectBriefService;
    @Override
    public SubjectTypeEnum getHandlerType() {
        return SubjectTypeEnum.BRIEF;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        List<SubjectBrief> subjectBriefList = new ArrayList<>();
        subjectInfoBO.getOptionLists().forEach(option -> {
            SubjectBrief subjectBrief = SubjectBriefConvert.INSTANCE.convert(option);
            subjectBrief.setSubjectId(subjectInfoBO.getId());
            subjectBrief.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
            subjectBriefList.add(subjectBrief);
        });
        subjectBriefService.batchInsert(subjectBriefList);
    }

    @Override
    public SubjectOptionBo query(int subjectId) {
        return null;
    }
}
