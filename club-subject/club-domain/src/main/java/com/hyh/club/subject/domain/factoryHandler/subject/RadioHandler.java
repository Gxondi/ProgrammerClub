package com.hyh.club.subject.domain.factoryHandler.subject;

import com.hyh.club.subject.common.enums.IsDeletedFlagEnum;
import com.hyh.club.subject.common.enums.SubjectTypeEnum;
import com.hyh.club.subject.domain.convert.SubjectRadioConvert;
import com.hyh.club.subject.domain.entity.SubjectAnswerBO;
import com.hyh.club.subject.domain.entity.SubjectInfoBO;
import com.hyh.club.subject.infra.basic.entity.SubjectRadio;
import com.hyh.club.subject.infra.basic.service.SubjectRadioService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class RadioHandler implements SubjectTypeHandler{
    @Resource
    private SubjectRadioService subjectRadioService;
    @Override
    public SubjectTypeEnum getHandlerType() {
        return SubjectTypeEnum.RADIO;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        List<SubjectRadio> subjectRadioList = new ArrayList<>();
        subjectInfoBO.getOptionList().forEach(option -> {
            SubjectRadio radio = SubjectRadioConvert.INSTANCE.convertBoToSubjectRadio(option);
            radio.setSubjectId(subjectInfoBO.getId());
            radio.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
            subjectRadioList.add(radio);
        });
        subjectRadioService.batchInsert(subjectRadioList);
    }
}
