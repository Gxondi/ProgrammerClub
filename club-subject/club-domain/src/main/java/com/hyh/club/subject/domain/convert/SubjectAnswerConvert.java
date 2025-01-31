package com.hyh.club.subject.domain.convert;

import com.hyh.club.subject.domain.entity.SubjectAnswerBO;
import com.hyh.club.subject.domain.entity.SubjectInfoBO;
import com.hyh.club.subject.infra.basic.entity.SubjectAnswer;
import com.hyh.club.subject.infra.basic.entity.SubjectInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectAnswerConvert {
    SubjectAnswerConvert INSTANCE = Mappers.getMapper(SubjectAnswerConvert.class);
    List<SubjectAnswer> convertBoToSubjectAnswer(List<SubjectAnswerBO> subjectAnswerBO);
    List<SubjectAnswerBO> convertSubjectInfoToBo(List<SubjectAnswer> subjectAnswerList);
}
