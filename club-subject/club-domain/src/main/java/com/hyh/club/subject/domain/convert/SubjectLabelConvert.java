package com.hyh.club.subject.domain.convert;

import com.hyh.club.subject.domain.entity.SubjectCategoryBO;
import com.hyh.club.subject.domain.entity.SubjectLabelBO;
import com.hyh.club.subject.infra.basic.entity.SubjectCategory;
import com.hyh.club.subject.infra.basic.entity.SubjectLabel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectLabelConvert {
    SubjectLabelConvert INSTANCE = Mappers.getMapper(SubjectLabelConvert.class);
    SubjectLabel convertBoToCategory(SubjectLabelBO subjectLabelBO);

    List<SubjectLabelBO> convertCategoryToBo(List<SubjectLabel> subjectLabelList);
}
