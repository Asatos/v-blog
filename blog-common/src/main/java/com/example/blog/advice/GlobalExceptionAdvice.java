package com.example.blog.advice;

import com.example.blog.exception.GlobalException;
import com.example.blog.vo.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ：wxt
 * @date ：Created in 2021/3/1 20:20
 * @description：全局异常处理
 * @modified By：
 * @version: 1.0
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {

    /**
     * 对GlobalException 进行拦截，统一处理
     * @param req
     * @param ex
     * @return
     */
    @ExceptionHandler(value = GlobalException.class)
    public R<String> handlerGlobalException(HttpServletRequest req, GlobalException ex) {
        R<String> response = new R<>(-1, "business error");
        response.setData(ex.getMessage());
        return response;
    }
}
