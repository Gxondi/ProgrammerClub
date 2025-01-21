package com.hyh.club.subject.domain.factoryHandler.subject;

import com.hyh.club.subject.common.enums.IsDeletedFlagEnum;
import com.hyh.club.subject.common.enums.SubjectTypeEnum;
import com.hyh.club.subject.domain.convert.SubjectJudgeConvert;
import com.hyh.club.subject.domain.convert.SubjectMultiConvert;
import com.hyh.club.subject.domain.convert.SubjectRadioConvert;
import com.hyh.club.subject.domain.entity.SubjectInfoBO;
import com.hyh.club.subject.infra.basic.entity.SubjectJudge;
import com.hyh.club.subject.infra.basic.entity.SubjectMultiple;
import com.hyh.club.subject.infra.basic.entity.SubjectRadio;
import com.hyh.club.subject.infra.basic.service.SubjectJudgeService;
import com.hyh.club.subject.infra.basic.service.SubjectRadioService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Component
public class JudgeHandler implements SubjectTypeHandler{
    @Resource
    private SubjectJudgeService subjectJudgeService;
    @Override
    public SubjectTypeEnum getHandlerType() {
        return SubjectTypeEnum.JUDGE;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        List<SubjectJudge> subjectJudgeList = new ArrayList<>();
        subjectInfoBO.getOptionList().forEach(option -> {
            SubjectJudge subjectJudge = SubjectJudgeConvert.INSTANCE.convert(option);
            subjectJudge.setSubjectId(subjectInfoBO.getId());
            subjectJudge.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
            subjectJudgeList.add(subjectJudge);
        });
        subjectJudgeService.batchInsert(subjectJudgeList);
    }
}
