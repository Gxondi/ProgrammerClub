package com.hyh.club.auth.common.enums;

import lombok.Getter;

@Getter
public enum AuthUserStatusEnum {
    CLOSE(1,"关闭"),
    OPEN(0,"启动");

    public int code;

    public String message;

    AuthUserStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * @author xf
     * @Date 2024/2/28 下午 04:36
     * @Description：TODO:  如何拿到 SUCCESS,FAIL
     * @Param
     * @return
     */
    public static AuthUserStatusEnum getByCode(int codeValue) {
        for (AuthUserStatusEnum statusEnum: AuthUserStatusEnum.values()) {
            if (statusEnum.code == codeValue) {
                return statusEnum;
            }
        }
        return null;
    }
}