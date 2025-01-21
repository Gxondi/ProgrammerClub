package com.hyh.club.subject.domain.factoryHandler.subject;

import com.hyh.club.subject.common.enums.SubjectTypeEnum;
import com.hyh.club.subject.domain.entity.SubjectInfoBO;
import org.springframework.stereotype.Component;

@Component
public interface SubjectTypeHandler {
    SubjectTypeEnum getHandlerType();

    void add(SubjectInfoBO subjectInfoBO);
}
