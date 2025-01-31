package com.hyh.club.subject.domain.service;

import com.hyh.club.subject.domain.entity.SubjectLabelBO;

import java.util.List;

public interface SubjectLabelDomainService {
    void add(SubjectLabelBO subjectLabelBO);

    boolean update(SubjectLabelBO subjectLabelBO);

    boolean delete(SubjectLabelBO subjectLabelBO);

    List<SubjectLabelBO> queryListByCategoryId(SubjectLabelBO subjectLabelBO);
}
