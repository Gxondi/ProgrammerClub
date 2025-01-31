package com.hyh.club.subject.domain.convert;

import com.hyh.club.subject.domain.entity.SubjectAnswerBO;
import com.hyh.club.subject.infra.basic.entity.SubjectJudge;
import com.hyh.club.subject.infra.basic.entity.SubjectRadio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubjectJudgeConvert {
    SubjectJudgeConvert INSTANCE = Mappers.getMapper(SubjectJudgeConvert.class);
    SubjectJudge convert(SubjectAnswerBO subjectAnswerBO);

}
