package com.hyh.club.subject.application.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 题目标签表(SubjectLabel)实体类
 *
 * @author Condix
 * @since 2025-01-20 17:42:21
 */
@Data
public class SubjectLabelDTO {
    /**
     * 主键
     */
    private Long id;
    /**
     * 标签分类
     */
    private String labelName;
    /**
     * 排序
     */
    private Integer sortNum;
    
    private String categoryId;
    
    private Integer isDeleted;

    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;

}

