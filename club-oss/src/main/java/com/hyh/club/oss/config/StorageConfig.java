package com.hyh.club.oss.config;

import com.hyh.club.oss.adapter.AliyunAdapter;
import com.hyh.club.oss.adapter.MinioAdapter;
import com.hyh.club.oss.adapter.StorageAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
public class StorageConfig {
    @Value("${storage.service.type}")
    private String storageType;
    @Bean
    @RefreshScope
    public StorageAdapter storageService() {
        System.out.println("storageType: " + storageType);
        if ("minio".equals(storageType)) {
            return new MinioAdapter();
        } else if ("aliyun".equals(storageType)) {
            return new AliyunAdapter();
        } else {
            throw new IllegalArgumentException("Unknown storage type: " + storageType);
        }
    }
}
