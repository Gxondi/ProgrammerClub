package com.hyh.club.oss.service;

import com.hyh.club.oss.adapter.StorageAdapter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FileService {
    @Resource
    private StorageAdapter storageAdapter;
    public FileService(StorageAdapter storageAdapter) {
        this.storageAdapter = storageAdapter;
    }
    public List<String> getAllBuckets() {
        return storageAdapter.getAllBuckets();
    }
}
