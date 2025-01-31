package com.hyh.club.subject.application.convert;

import com.hyh.club.subject.application.dto.SubjectCategoryDTO;
import com.hyh.club.subject.application.dto.SubjectLabelDTO;
import com.hyh.club.subject.domain.entity.SubjectCategoryBO;
import com.hyh.club.subject.domain.entity.SubjectLabelBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectLabelDTOConvert {
    SubjectLabelDTOConvert INSTANCE = Mappers.getMapper(SubjectLabelDTOConvert.class);
    SubjectLabelBO convertDTOToLabelBO(SubjectLabelDTO subjectLabelDTO);
    List<SubjectLabelDTO> convertBOToLabelDTO(List<SubjectLabelBO> subjectLabelBOList);
}
