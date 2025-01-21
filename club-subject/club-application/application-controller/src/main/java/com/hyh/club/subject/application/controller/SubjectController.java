package com.hyh.club.subject.application.controller;

import com.google.common.base.Preconditions;
import com.hyh.club.subject.application.convert.SubjectAnswerConvert;
import com.hyh.club.subject.application.convert.SubjectInfoDTOConvert;
import com.hyh.club.subject.application.convert.SubjectLabelDTOConvert;
import com.hyh.club.subject.application.dto.SubjectInfoDTO;
import com.hyh.club.subject.application.dto.SubjectLabelDTO;
import com.hyh.club.subject.common.result.Result;
import com.hyh.club.subject.domain.entity.SubjectAnswerBO;
import com.hyh.club.subject.domain.entity.SubjectInfoBO;
import com.hyh.club.subject.domain.entity.SubjectLabelBO;
import com.hyh.club.subject.domain.service.SubjectInfoDomainService;
import com.hyh.club.subject.domain.service.SubjectLabelDomainService;
import com.hyh.club.subject.infra.basic.entity.SubjectLabel;
import com.hyh.club.subject.infra.basic.service.SubjectInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/subject")
@Slf4j
public class SubjectController {
    @Resource
    private SubjectInfoDomainService subjectInfoDomainService;

    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectInfoDTO subjectInfoDTO) {
        SubjectInfoBO subjectInfoBO = SubjectInfoDTOConvert.INSTANCE.convertDTOToSubjectInfoBO(subjectInfoDTO);
        Preconditions.checkNotNull(subjectInfoBO.getSubjectName(), "题目名称不可为空");
        Preconditions.checkNotNull(subjectInfoBO.getSubjectType(), "题目类型不可为空");
        Preconditions.checkNotNull(subjectInfoBO.getSubjectDifficult(), "题目难度不可为空");
        List<SubjectAnswerBO> subjectAnswerBOList = SubjectAnswerConvert.INSTANCE.convertDTOToAnswerBO(subjectInfoDTO.getOptionList());
        subjectInfoBO.setOptionList(subjectAnswerBOList);
        subjectInfoDomainService.add(subjectInfoBO);
        return Result.OK(true);
    }

    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        return Result.OK();
    }

    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        return Result.OK();
    }

}
