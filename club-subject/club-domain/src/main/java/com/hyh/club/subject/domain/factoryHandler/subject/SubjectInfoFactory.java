package com.hyh.club.subject.domain.factoryHandler.subject;

import com.hyh.club.subject.common.enums.SubjectTypeEnum;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Component
public class SubjectInfoFactory implements InitializingBean {
    @Resource
    private List<SubjectTypeHandler> subjectTypeHandlerList;
    private HashMap<SubjectTypeEnum,SubjectTypeHandler> handlerHashMap = new HashMap<>();
    public SubjectTypeHandler getHandler(int subjectType){
        SubjectTypeEnum subjectTypeEnum = SubjectTypeEnum.getByCode(subjectType);
        return handlerHashMap.get(subjectTypeEnum);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        for (SubjectTypeHandler subjectTypeHandler : subjectTypeHandlerList) {
            handlerHashMap.put(subjectTypeHandler.getHandlerType(),subjectTypeHandler);
        }
    }
}
