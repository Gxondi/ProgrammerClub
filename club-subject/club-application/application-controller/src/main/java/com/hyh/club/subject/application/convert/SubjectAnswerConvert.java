package com.hyh.club.subject.application.convert;

import com.hyh.club.subject.application.dto.SubjectAnswerDTO;
import com.hyh.club.subject.application.dto.SubjectInfoDTO;
import com.hyh.club.subject.domain.entity.SubjectAnswerBO;
import com.hyh.club.subject.domain.entity.SubjectInfoBO;
import com.hyh.club.subject.infra.basic.entity.SubjectAnswer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectAnswerConvert {
    SubjectAnswerConvert INSTANCE = Mappers.getMapper(SubjectAnswerConvert.class);
    List<SubjectAnswerBO> convertDTOToAnswerBO(List<SubjectAnswerDTO> subjectAnswerDTO);
    List<SubjectAnswerDTO> convertBOToAnswerDTO(List<SubjectAnswerBO> subjectAnswerBOList);
}
