package com.lvzz.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    public Result(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }

    private static final int SUCCESS_CODE=200;
    private static final int ERROR_CODE=201;

    private static final String SUCCESS_MSG="success";
    private static final String ERROR_MSG="error";

    public static <T> com.syxu.yitu.entity.Result<T> success(T data){
        return new com.syxu.yitu.entity.Result<>(SUCCESS_CODE,SUCCESS_MSG,data);

    }

    public static <T> com.syxu.yitu.entity.Result<T> error(T data){
        return new com.syxu.yitu.entity.Result<>(ERROR_CODE,ERROR_MSG,data);

    }

    public static <T> com.syxu.yitu.entity.Result<T> success(){
        return new com.syxu.yitu.entity.Result<>(SUCCESS_CODE,SUCCESS_MSG);

    }

    public static <T> com.syxu.yitu.entity.Result<T> error(){
        return new com.syxu.yitu.entity.Result<>(ERROR_CODE,ERROR_MSG);
    }

    public static <T> com.syxu.yitu.entity.Result<T> othereError(Integer code, String msg){
        return new com.syxu.yitu.entity.Result<>(code,msg);
    }
}
