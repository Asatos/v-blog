package com.example.blog.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author ：wxt
 * @date ：Created in 2021/3/1 18:28
 * @description： 定制Http消息转换器
 * @modified By：
 * @version: 1.0
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    /**
     * 配置 http消息的转换器
     * 将Java实体对象转换成Http的数据的输出流
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.clear();
        // MappingJackson2HttpMessageConverter 可以将Java实体对象转换成json数据格式
        converters.add(new MappingJackson2HttpMessageConverter());
    }
}
