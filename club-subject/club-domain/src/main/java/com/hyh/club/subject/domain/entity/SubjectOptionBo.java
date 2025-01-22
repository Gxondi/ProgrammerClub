package com.hyh.club.subject.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SubjectOptionBo implements Serializable {
    /**
     * 答案选项
     */
    private List<SubjectAnswerBO> optionLists;
    /**
     * 题目答案
     */
    private String subjectAnswers;
}