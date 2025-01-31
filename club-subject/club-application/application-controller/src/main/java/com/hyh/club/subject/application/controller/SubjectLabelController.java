package com.hyh.club.subject.application.controller;

import com.google.common.base.Preconditions;
import com.hyh.club.subject.application.convert.SubjectLabelDTOConvert;
import com.hyh.club.subject.application.dto.SubjectCategoryDTO;
import com.hyh.club.subject.application.dto.SubjectLabelDTO;
import com.hyh.club.subject.common.result.Result;
import com.hyh.club.subject.domain.entity.SubjectLabelBO;
import com.hyh.club.subject.domain.service.SubjectLabelDomainService;
import com.hyh.club.subject.infra.basic.entity.SubjectCategory;
import com.hyh.club.subject.infra.basic.entity.SubjectLabel;
import com.hyh.club.subject.infra.basic.service.SubjectCategoryService;
import com.hyh.club.subject.infra.basic.service.SubjectLabelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/subject/label")
@Slf4j
public class SubjectLabelController {
    @Resource
    private SubjectLabelDomainService  subjectLabelDomainService;
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectLabelDTO subjectLabelDTO){
        try {
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConvert.INSTANCE.convertDTOToLabelBO(subjectLabelDTO);
            Preconditions.checkNotNull(subjectLabelBO.getLabelName(), "标签名不可为空");
            Preconditions.checkNotNull(subjectLabelBO.getCategoryId(), "分类ID不可为空");
            subjectLabelDomainService.add(subjectLabelBO);
            return Result.OK(true);
        } catch (Exception e) {
            log.info(e.getMessage(),e);
            return Result.FAIL(false);
        }
    }

    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody SubjectLabelDTO subjectLabelDTO){
        try {
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConvert.INSTANCE.convertDTOToLabelBO(subjectLabelDTO);
            Preconditions.checkNotNull(subjectLabelBO.getId(), "ID不可为空");
            Preconditions.checkNotNull(subjectLabelBO.getLabelName(), "标签名不可为空");
            Preconditions.checkNotNull(subjectLabelBO.getCategoryId(), "分类ID不可为空");
            boolean result = subjectLabelDomainService.update(subjectLabelBO);
            return Result.OK(result);
        } catch (Exception e) {
            log.info(e.getMessage(),e);
            return Result.FAIL(false);
        }
    }

    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody SubjectLabelDTO subjectLabelDTO){
        try {
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConvert.INSTANCE.convertDTOToLabelBO(subjectLabelDTO);
            Preconditions.checkNotNull(subjectLabelBO.getId(), "ID不可为空");
            boolean result = subjectLabelDomainService.delete(subjectLabelBO);
            return Result.OK(result);
        } catch (Exception e) {
            log.info(e.getMessage(),e);
            return Result.FAIL(false);
        }
    }

    @PostMapping("/queryListByCategoryId")
    public Result<List<SubjectLabel>> queryListByCategoryId(@RequestBody SubjectLabelDTO subjectLabelDTO){
        try {
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConvert.INSTANCE.convertDTOToLabelBO(subjectLabelDTO);
            Preconditions.checkNotNull(subjectLabelBO.getCategoryId(), "分类ID不可为空");
            List<SubjectLabelBO> subjectLabelBOList = subjectLabelDomainService.queryListByCategoryId(subjectLabelBO);
            List<SubjectLabelDTO> subjectLabelDTOS = SubjectLabelDTOConvert.INSTANCE.convertBOToLabelDTO(subjectLabelBOList);
            return Result.OK(subjectLabelDTOS);
        } catch (Exception e) {
            log.info(e.getMessage(),e);
            return Result.FAIL(false);
        }
    }
}
