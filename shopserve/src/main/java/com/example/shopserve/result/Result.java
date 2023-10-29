package com.example.shopserve.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@Schema(name = "Result", description = "通用返回对象")
public class Result<T> {

    @Schema(name = "data", description = "数据封装")
    private T data;
    @Schema(name = "code", description = "状态码<200：成功，404：未找到资源，500：服务器错误>")
    private int code;
    @Schema(name = "msg", description = "提示信息")
    private String msg;

    private Result(int status, T data, String msg){
        this.code = status;
        this.data = data;
        this.msg = msg;
    }

    public static<E> Result<E> ok(E data){
        return new Result<>(HttpStatus.OK.value(), data, "OK");
    }

    public static<E> Result<E> error(){
        return new Result<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, "服务内部错误");
    }

    public static<E> Result<E> error(int status, String msg){
        return new Result<>(status, null, msg);
    }
}
