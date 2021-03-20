package com.example.blog.advice;

import com.example.blog.annotation.IgnoreResponseAdvice;
import com.example.blog.vo.R;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author ：wxt
 * @date ：Created in 2021/3/1 19:38
 * @description： 定义统一响应格式
 * ResponseBodyAdvice 用于定义对响应处理的接口
 * @modified By：
 * @version: 1.0
 */
@RestControllerAdvice
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {
    /**
     * 判断是否需要对响应进行处理 true 需要处理 false 不需要
     * @param returnType controller方法的定义
     * @param converterType http请求转换器
     * @return
     */
    @Override
    public boolean supports(MethodParameter returnType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        //如果当前类有@IgnoreResponseAdvice注解 则 不需要对响应处理
        if (returnType.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        //如果当前方法有@IgnoreResponseAdvice注解 则 不需要对响应处理
        if (returnType.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        if ("getDocumentation".equals(returnType.getExecutable().getName())) {
            return false;
        }
        //对响应进行处理，执行beforeBodyWrite方法
        return true;
    }

    /**
     * 响应之前处理
     * beforeBodyWrite 就是 在响应写入response body之前进行处理
     * @param body controller 返回对象
     * @param returnType controller方法的定义
     * @param selectedContentType http请求转换器
     * @param selectedConverterType
     * @param serverHttpRequest 请求
     * @param serverHttpResponse  响应
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        //定义最终的返回对象
        R<Object> response = new R<>(0, "");
        //如果 body 是null，代表controller方法返回是void,response 不需要设置data
        //如果 body 是 CommonResponse类型 不需要处理
        //否则把响应对象作为data
        if (null == body) {
            return response;
        } else if (body instanceof R) {
            response = (R<Object>) body;
        } else {
            response.setData(body);
        }

        return response;
    }
}
