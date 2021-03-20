package com.example.blog.controller;

import com.example.blog.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：wxt
 * @date ：Created in 2021/3/20
 * @version: 1.0
 */
@Api(tags = "博客后台接口")
@Slf4j
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Value(("${spring.application.name}"))
    private String applicationName;

    @Value("${server.port}")
    private String port;

    @ApiOperation(value = "获取端口信息",tags = "博客后台接口")
    @GetMapping("/port")
    public R getPort() {
        return R.ok("当前运行的端口号:"+port);
    }

    @ApiOperation(value = "获取服务名",tags = "博客后台接口")
    @GetMapping("/name")
    public R getContextPath() {
        return R.ok("当前访问服务名:"+applicationName);
    }
}
