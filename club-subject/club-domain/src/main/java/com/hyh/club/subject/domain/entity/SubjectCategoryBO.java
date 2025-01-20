package com.hyh.club.subject.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 题目分类(SubjectCategory)实体类BO
 *
 * @author Condix
 * @since 2025-01-17 14:40:27
 */
@Data
public class SubjectCategoryBO implements Serializable {
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
}

