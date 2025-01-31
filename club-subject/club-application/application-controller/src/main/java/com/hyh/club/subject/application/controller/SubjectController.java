package com.hyh.club.subject.application.controller;

import com.google.common.base.Preconditions;
import com.hyh.club.subject.application.convert.SubjectAnswerConvert;
import com.hyh.club.subject.application.convert.SubjectInfoDTOConvert;
import com.hyh.club.subject.application.dto.SubjectInfoDTO;
import com.hyh.club.subject.common.result.PageResult;
import com.hyh.club.subject.common.result.Result;
import com.hyh.club.subject.domain.entity.SubjectAnswerBO;
import com.hyh.club.subject.domain.entity.SubjectInfoBO;
import com.hyh.club.subject.domain.service.SubjectInfoDomainService;
import com.hyh.club.subject.infra.basic.entity.SubjectInfo;
import lombok.extern.slf4j.Slf4j;
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
        try {
            SubjectInfoBO subjectInfoBO = SubjectInfoDTOConvert.INSTANCE.convertDTOToSubjectInfoBO(subjectInfoDTO);
            Preconditions.checkNotNull(subjectInfoBO.getSubjectName(), "题目名称不可为空");
            Preconditions.checkNotNull(subjectInfoBO.getSubjectType(), "题目类型不可为空");
            Preconditions.checkNotNull(subjectInfoBO.getSubjectDifficult(), "题目难度不可为空");
            List<SubjectAnswerBO> subjectAnswerBOList = SubjectAnswerConvert.INSTANCE.convertDTOToAnswerBO(subjectInfoDTO.getOptionList());
            subjectInfoBO.setOptionLists(subjectAnswerBOList);
            subjectInfoDomainService.add(subjectInfoBO);
            return Result.OK(true);
        } catch (Exception e) {
            return Result.FAIL(false);
        }
    }

    @PostMapping("/querySubjectList")
    public Result<PageResult<SubjectInfoDTO>> querySubjectList(@RequestBody SubjectInfoDTO subjectInfoDTO) {
        try {
            SubjectInfoBO subjectInfoBO = SubjectInfoDTOConvert.INSTANCE.convertDTOToSubjectInfoBO(subjectInfoDTO);
            subjectInfoBO.setSubjectLabelId(subjectInfoDTO.getSubjectLabelId());
            subjectInfoBO.setSubjectCategoryId(subjectInfoDTO.getSubjectCategoryId());
            Preconditions.checkNotNull(subjectInfoBO.getSubjectLabelId(), "标签ID不可为空");
            Preconditions.checkNotNull(subjectInfoBO.getSubjectCategoryId(), "类型ID不可为空");
            PageResult<SubjectInfoBO> pageResult = subjectInfoDomainService.querySubjectList(subjectInfoBO);
            PageResult<SubjectInfoDTO> subjectInfoDTOS = SubjectInfoDTOConvert.INSTANCE.convertBOToSubjectInfoPageDTO(pageResult);
            return Result.OK(subjectInfoDTOS);
        } catch (Exception e) {
            return Result.FAIL(e.getMessage());
        }
    }

    @PostMapping("/SubjectInfo")
    public Result<SubjectInfoBO> SubjectInfo(@RequestBody SubjectInfoDTO subjectInfoDTO) {
        try {
            SubjectInfoBO subjectInfoBO = SubjectInfoDTOConvert.INSTANCE.convertDTOToSubjectInfoBO(subjectInfoDTO);
            subjectInfoBO.setSubjectLabelId(subjectInfoDTO.getSubjectLabelId());
            subjectInfoBO.setSubjectCategoryId(subjectInfoDTO.getSubjectCategoryId());
            Preconditions.checkNotNull(subjectInfoBO.getSubjectLabelId(), "标签ID不可为空");
            Preconditions.checkNotNull(subjectInfoBO.getId(), "ID不可为空");
            Preconditions.checkNotNull(subjectInfoBO.getSubjectCategoryId(), "类型ID不可为空");
            SubjectInfoBO subjectInfoByCondition = subjectInfoDomainService.subjectInfoByCondition(subjectInfoBO);
            return Result.OK(subjectInfoByCondition);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
