package com.hyh.club.subject.domain.convert;

import com.hyh.club.subject.domain.entity.SubjectCategoryBO;
import com.hyh.club.subject.infra.basic.entity.SubjectCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectCategoryConvert {
    SubjectCategoryConvert INSTANCE = Mappers.getMapper(SubjectCategoryConvert.class);
    SubjectCategory convertBoToCategory(SubjectCategoryBO subjectCategoryBo);

    List<SubjectCategoryBO> convertCategoryToBo(List<SubjectCategory> subjectCategoryList);
}
