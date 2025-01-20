package com.hyh.club.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.hyh.club.subject.application.convert.SubjectCategoryDTOConvert;
import com.hyh.club.subject.application.dto.SubjectCategoryDTO;
import com.hyh.club.subject.common.result.Result;
import com.hyh.club.subject.domain.entity.SubjectCategoryBO;
import com.hyh.club.subject.domain.service.SubjectCategoryDomainService;
import lombok.extern.log4j.Log4j2;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/subject/category")
@Log4j2
public class SubjectCategoryController {
    @Resource
    private SubjectCategoryDomainService subjectCategoryDomainService;

    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("com.hyh.club.subject.application.dto:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            Preconditions.checkNotNull(subjectCategoryDTO.getCategoryType(), "分类类型不可为空");
            Preconditions.checkArgument(!StringUtils.isEmpty(subjectCategoryDTO.getCategoryName()), "分类名称不可为空");
            Preconditions.checkNotNull(subjectCategoryDTO.getParentId(), "分类父类不可为空");
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConvert.INSTANCE.convertDTOToCategoryBO(subjectCategoryDTO);
            subjectCategoryDomainService.add(subjectCategoryBO);
            return Result.OK(true);
        } catch (Exception e) {
            log.info("com.hyh.club.subject.application.dto.error:{}", e.getMessage(), e);
            return Result.FAIL(e.getMessage());
        }
    }
    @PostMapping("/queryPrimaryCategory")
    public Result<List<SubjectCategoryDTO>> queryPrimaryCategory(){
        try {
            SubjectCategoryDTO subjectCategoryDTO = new SubjectCategoryDTO();
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConvert.INSTANCE.convertDTOToCategoryBO(subjectCategoryDTO);
            List<SubjectCategoryBO> subjectCategoryBOList = subjectCategoryDomainService.queryCategory(subjectCategoryBO);
            List<SubjectCategoryDTO> subjectCategoryDTOList = SubjectCategoryDTOConvert.INSTANCE.convertBOToCategoryDTO(subjectCategoryBOList);
            return Result.OK(subjectCategoryDTOList);
        } catch (Exception e) {
            log.info("com.hyh.club.subject.application.dto.queryPrimaryCategory.error:{}", e.getMessage(), e);
            return Result.FAIL(e.getMessage());
        }
    }

    @PostMapping("/queryCategoryByPrimary")
    public Result<List<SubjectCategoryDTO>> queryCategoryByPrimary(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try {
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConvert.INSTANCE.convertDTOToCategoryBO(subjectCategoryDTO);
            Preconditions.checkNotNull(subjectCategoryDTO.getParentId(), "分类父类不可为空");
            List<SubjectCategoryBO> subjectCategoryBOList = subjectCategoryDomainService.queryCategory(subjectCategoryBO);
            List<SubjectCategoryDTO> subjectCategoryDTOList = SubjectCategoryDTOConvert.INSTANCE.convertBOToCategoryDTO(subjectCategoryBOList);
            return Result.OK(subjectCategoryDTOList);
        } catch (Exception e) {
            log.info("com.hyh.club.subject.application.dto.queryPrimaryCategory.error:{}", e.getMessage(), e);
            return Result.FAIL(e.getMessage());
        }
    }
}
