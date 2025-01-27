package com.hyh.club.oss.adapter;

import com.hyh.club.oss.entity.FileInfo;

import java.util.List;
import java.io.InputStream;



public interface StorageAdapter {
    /**
     * 創建bucket
     * @param bucketName
     */
    void createBucket(String bucketName);

    /**
     * 上傳文件
     * @param inputStream
     * @param bucketName
     * @param objectName
     */
    void uploadFile(InputStream inputStream, String bucketName, String objectName ) ;

    /**
     *
     * @return 返回所有的bucket
     */
    List<String> getAllBuckets() ;

    /**
     *
     * @param bucketName
     * @return 返回bucket下的所有文件
     */
    List<FileInfo> getAllObjectsByBucketName(String bucketName) ;

    /**
     * 下載文件
     * @param bucketName
     * @param objectName
     * @return
     */
    InputStream downloadFile(String bucketName, String objectName);

    void deleteObject(String bucket, String objectName) ;

    void deleteBucket(String bucket) ;
}
