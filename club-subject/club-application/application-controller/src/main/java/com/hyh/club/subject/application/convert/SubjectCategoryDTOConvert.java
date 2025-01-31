package com.hyh.club.subject.application.convert;

import com.hyh.club.subject.application.dto.SubjectCategoryDTO;
import com.hyh.club.subject.domain.entity.SubjectCategoryBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectCategoryDTOConvert {
    SubjectCategoryDTOConvert INSTANCE = Mappers.getMapper(SubjectCategoryDTOConvert.class);
    SubjectCategoryBO convertDTOToCategoryBO(SubjectCategoryDTO subjectCategoryDTO);

    List<SubjectCategoryDTO> convertBOToCategoryDTO(List<SubjectCategoryBO> subjectCategoryBOList);
}
