package me.uuus.sue4j.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.Properties;

/**
 * 异常处理类,总不能放用户看你的什么异常信息之类的啊  不安全不友好
 * @author Mr.Su[swb0917@gmail.com]
 */
@Configuration
public class ExceptionConfig {

    @Bean
    public SimpleMappingExceptionResolver exceptionMappings() {
        SimpleMappingExceptionResolver smer = new SimpleMappingExceptionResolver();
        Properties properties = new Properties();
        // 未认证异常
        properties.setProperty("org.apache.shiro.authz.UnauthenticatedException", "/user/logout");
        // 权限不够异常
        properties.setProperty("org.apache.shiro.authz.UnauthorizedException", "/403");
        // 其他的所有异常
        properties.setProperty("java.lang.Throwable", "/500");
        smer.setExceptionMappings(properties);
        return smer;
    }

}
