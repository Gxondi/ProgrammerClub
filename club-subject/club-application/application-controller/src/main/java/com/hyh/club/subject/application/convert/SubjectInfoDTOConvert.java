package com.hyh.club.subject.application.convert;

import com.hyh.club.subject.application.dto.SubjectInfoDTO;
import com.hyh.club.subject.application.dto.SubjectLabelDTO;
import com.hyh.club.subject.domain.entity.SubjectInfoBO;
import com.hyh.club.subject.domain.entity.SubjectLabelBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectInfoDTOConvert {
    SubjectInfoDTOConvert INSTANCE = Mappers.getMapper(SubjectInfoDTOConvert.class);
    SubjectInfoBO convertDTOToSubjectInfoBO(SubjectInfoDTO subjectInfoDTO);
    List<SubjectInfoDTO> convertBOToSubjectInfoDTO(List<SubjectInfoBO> subjectInfoBOS);
}
