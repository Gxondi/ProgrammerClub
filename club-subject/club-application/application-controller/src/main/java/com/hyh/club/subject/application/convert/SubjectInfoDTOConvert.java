package com.hyh.club.subject.application.convert;

import com.hyh.club.subject.application.dto.SubjectInfoDTO;
import com.hyh.club.subject.common.result.PageResult;
import com.hyh.club.subject.domain.entity.SubjectInfoBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectInfoDTOConvert {
    SubjectInfoDTOConvert INSTANCE = Mappers.getMapper(SubjectInfoDTOConvert.class);
    SubjectInfoBO convertDTOToSubjectInfoBO(SubjectInfoDTO subjectInfoDTO);
    PageResult<SubjectInfoDTO> convertBOToSubjectInfoPageDTO(PageResult<SubjectInfoBO> subjectInfoBOS);
    SubjectInfoDTO convertBOToSubjectInfoDTO(SubjectInfoBO subjectInfoByCondition);
}
