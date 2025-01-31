package com.hyh.club.subject.application.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * 空值不返回
 */
@Configuration
public class GlobalConfig extends WebMvcConfigurationSupport {
    @Override
    //继承父类，重写
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //先实现父类
        super.configureMessageConverters(converters);
        //add方法
        converters.add(mappingJackson2HttpMessageConverter());
    }

    //针对jackson做空值处理，DTO在接口中返回的null字段处理。
    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(objectMapper);
        return converter;
    }
}
