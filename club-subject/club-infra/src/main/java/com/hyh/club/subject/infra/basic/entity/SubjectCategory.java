package com.hyh.club.subject.infra.basic.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 题目分类(SubjectCategory)实体类
 *
 * @author Condix
 * @since 2025-01-17 14:40:27
 */
@Data
public class SubjectCategory implements Serializable {
    private static final long serialVersionUID = -57474269395030120L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 分类名称
     */
    private String categoryName;
    /**
     * 分类类型
     */
    private Integer categoryType;
    /**
     * 图标连接
     */
    private String imageUrl;
    /**
     * 父级id
     */
    private Long parentId;

    /**
     * 是否删除 0: 未删除 1: 已删除
     */
    private Integer isDeleted;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建人
     */
    private Long createdBy;
    /**
     * 更新人
     */
    private Long updateBy;

}

