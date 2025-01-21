package com.hyh.club.subject.domain.entity;

import lombok.Data;

@Data
public class SubjectRadioBO {
    private Integer optionType;
    private String optionContent;
    private Integer isCorrect;
}
