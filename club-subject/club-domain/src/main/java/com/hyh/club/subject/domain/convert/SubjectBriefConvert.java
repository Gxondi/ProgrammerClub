package com.hyh.club.subject.domain.convert;

import com.hyh.club.subject.domain.entity.SubjectAnswerBO;
import com.hyh.club.subject.infra.basic.entity.SubjectBrief;
import com.hyh.club.subject.infra.basic.entity.SubjectRadio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubjectBriefConvert {
    SubjectBriefConvert INSTANCE = Mappers.getMapper(SubjectBriefConvert.class);
    SubjectBrief convert(SubjectAnswerBO subjectAnswerBO);
}
