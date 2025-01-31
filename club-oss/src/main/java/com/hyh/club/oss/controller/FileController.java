package com.hyh.club.oss.controller;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.hyh.club.oss.service.FileService;
import com.hyh.club.oss.utils.MinioUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
@RefreshScope
@RestController
@RequestMapping("/oss")
public class FileController {

    @Resource
    private FileService fileService;
    @Value(value = "${storage.service.type}")
    private String storageType;


    @RequestMapping("/test")
    public String test() {
        return fileService.getAllBuckets().get(0);
    }

    @RequestMapping("/testNacos")
    public String testNacos() {
        System.out.println("storageType: " + storageType);
        return storageType;
    }
}
