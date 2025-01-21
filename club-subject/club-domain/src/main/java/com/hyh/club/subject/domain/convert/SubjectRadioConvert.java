package com.hyh.club.subject.domain.convert;

import com.hyh.club.subject.domain.entity.SubjectAnswerBO;
import com.hyh.club.subject.domain.entity.SubjectRadioBO;
import com.hyh.club.subject.infra.basic.entity.SubjectAnswer;
import com.hyh.club.subject.infra.basic.entity.SubjectRadio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectRadioConvert {
    SubjectRadioConvert INSTANCE = Mappers.getMapper(SubjectRadioConvert.class);
    SubjectRadio convertBoToSubjectRadio(SubjectAnswerBO subjectAnswerBO);
}
