package com.hyh.club.subject.common.enums;

import lombok.Getter;

@Getter
public enum SubjectTypeEnum {
    RADIO(1, "单选"),
    MULTI(2, "多选"),
    JUDGE(3,"判断"),
    BRIEF(4,"解答");

    private int code;
    private String desc;

    SubjectTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static SubjectTypeEnum getByCode(int codeVal) {
        for (SubjectTypeEnum resultCodeEnum : SubjectTypeEnum.values()) {
            if (resultCodeEnum.code == codeVal) {
                return resultCodeEnum;
            }
        }
        return null;
    }
}
