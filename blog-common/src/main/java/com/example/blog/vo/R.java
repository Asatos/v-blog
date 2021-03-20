package com.example.blog.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ：wxt
 * @date ：Created in 2021/3/1 19:29
 * @description： 统一响应对象定义
 * @modified By：
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("统一返回结果")
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("响应码")
    private int code;
    @ApiModelProperty("响应信息")
    private String msg;
    @ApiModelProperty("响应体")
    private T data;

    public R(int code,String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    private static <T> R<T> restResult(int code, String msg, T data) {
        return new R(code, msg, data);
    }

    public static <T> R ok(T data) {
        return restResult(0, "success", data);
    }

    public static <T> R exception(Throwable e) {
        return restResult(1, e.getMessage(), e);
    }

    public static R ok() {
        return ok((Object)null);
    }

    public static R fail(int code) {
        return restResult(1, "fail", (Object)null);
    }

    public static R fail(int code, String msg) {
        return restResult(code, msg, (Object)null);
    }

    public static <T> R fail() {
        return fail(1);
    }

    public static <T> R fail(String msg) {
        return fail(1, msg);
    }

    @Override
    public String toString() {
        return "R(code=" + this.getCode() + ", msg=" + this.getMsg() + ", data=" + this.getData() + ")";
    }
}
