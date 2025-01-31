package com.hyh.club.subject.application.dto;

import lombok.Data;

@Data
public class SubjectQueryDTO {
    private Integer pageIndex;
    private Integer pageSize;
    private Integer labelId;
    private Integer categoryId;
    private Integer difficulty;
}
