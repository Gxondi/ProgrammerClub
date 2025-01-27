package com.hyh.club.oss.adapter;

import com.hyh.club.oss.entity.FileInfo;
import com.hyh.club.oss.utils.MinioUtil;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;
public class MinioAdapter implements StorageAdapter {
    @Resource
    private MinioUtil minioUtil;
    @Override
    public void createBucket(String bucketName) {

    }

    @Override
    public void uploadFile(InputStream inputStream, String bucketName, String objectName) {

    }

    @Override
    public List<String> getAllBuckets() {
        return minioUtil.getAllBuckets();
    }

    @Override
    public List<FileInfo> getAllObjectsByBucketName(String bucketName) {
        return null;
    }

    @Override
    public InputStream downloadFile(String bucketName, String objectName) {
        return null;
    }

    @Override
    public void deleteObject(String bucket, String objectName) {

    }

    @Override
    public void deleteBucket(String bucket) {

    }
}
