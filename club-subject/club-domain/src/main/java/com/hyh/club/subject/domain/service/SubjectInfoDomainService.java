package com.hyh.club.subject.domain.service;

import com.hyh.club.subject.common.result.PageResult;
import com.hyh.club.subject.domain.entity.SubjectInfoBO;
import com.hyh.club.subject.infra.basic.entity.SubjectInfo;

public interface SubjectInfoDomainService {
    void add(SubjectInfoBO subjectInfoBO);

    PageResult<SubjectInfoBO> querySubjectList(SubjectInfoBO subjectInfoBO);

    SubjectInfoBO subjectInfoByCondition(SubjectInfoBO subjectInfoBO);
}
