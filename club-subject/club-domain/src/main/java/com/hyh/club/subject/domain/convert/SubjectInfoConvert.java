package com.hyh.club.subject.domain.convert;

import com.hyh.club.subject.domain.entity.SubjectInfoBO;
import com.hyh.club.subject.domain.entity.SubjectLabelBO;
import com.hyh.club.subject.infra.basic.entity.SubjectInfo;
import com.hyh.club.subject.infra.basic.entity.SubjectLabel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectInfoConvert {
    SubjectInfoConvert INSTANCE = Mappers.getMapper(SubjectInfoConvert.class);
    SubjectInfo convertBoToSubjectInfo(SubjectInfoBO subjectInfoBO);

    List<SubjectInfoBO> convertSubjectInfoToBo(List<SubjectInfo> subjectInfoList);
}
