package com.hyh.club.subject.domain.convert;

import com.hyh.club.subject.domain.entity.SubjectAnswerBO;
import com.hyh.club.subject.infra.basic.entity.SubjectMultiple;
import com.hyh.club.subject.infra.basic.entity.SubjectRadio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubjectMultiConvert {
    SubjectMultiConvert INSTANCE = Mappers.getMapper(SubjectMultiConvert.class);
    SubjectMultiple convert(SubjectAnswerBO subjectAnswerBO);

}
