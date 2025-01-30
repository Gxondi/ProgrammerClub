package com.hyh.club.auth.common.result;

import com.hyh.club.auth.common.enums.ResultCodeEnum;
import lombok.Data;

@Data
public class Result<T> {
    private Boolean success;
    private Integer code;
    private String desc;
    private T data;

    public static <T> Result OK(){
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setDesc(ResultCodeEnum.SUCCESS.getDesc());
        return result;
    }

    public static <T> Result OK(T data){
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setDesc(ResultCodeEnum.SUCCESS.getDesc());
        result.setData(data);
        return result;
    }

    public static <T> Result FAIL(){
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(ResultCodeEnum.FAIL.getCode());
        result.setDesc(ResultCodeEnum.FAIL.getDesc());
        return result;
    }

    public static <T> Result FAIL(T data){
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(ResultCodeEnum.FAIL.getCode());
        result.setDesc(ResultCodeEnum.FAIL.getDesc());
        result.setData(data);
        return result;
    }
}
