package com.hyh.club.oss.utils;

import com.hyh.club.oss.config.MinioConfig;
import com.hyh.club.oss.entity.FileInfo;
import io.minio.*;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MinioUtil {
    @Resource
    private MinioClient minioClient;
    @Resource
    private MinioConfig minioConfig;

    /**
     * 創建bucket
     * @param bucketName
     */
    public void createBucket(String bucketName) {
        try {
            MinioClient minioClient = minioConfig.getMinioClient();

            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!isExist) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 上傳文件
     * @param inputStream
     * @param bucketName
     * @param objectName
     */
    public void uploadFile(InputStream inputStream, String bucketName, String objectName ) {
        try {
            MinioClient minioClient = minioConfig.getMinioClient();

            minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(objectName)
                    .stream(inputStream,-1,Integer.MAX_VALUE).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return 返回所有的bucket
     */
    public List<String> getAllBuckets() {
        try {
            MinioClient minioClient = minioConfig.getMinioClient();

            List<Bucket> bucketList = minioClient.listBuckets();
            return bucketList.stream().map(Bucket::name).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    /**
     *
     * @param bucketName
     * @return 返回bucket下的所有文件
     */
    public List<FileInfo> getAllObjectsByBucketName(String bucketName) {
        try {
            MinioClient minioClient = minioConfig.getMinioClient();

            Iterable<Result<Item>> results = minioClient.listObjects(ListObjectsArgs.builder().bucket(bucketName).build());
            List<FileInfo> fileInfoList = new ArrayList<>();
            for (Result<Item> result : results) {
                FileInfo fileInfo = new FileInfo();
                Item item = result.get();
                fileInfo.setFileName(item.objectName());
                fileInfo.setEtag(item.etag());
                fileInfo.setDirectoryFlag(item.isDir());
                fileInfoList.add(fileInfo);
            }
            return fileInfoList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    /**
     * 下載文件
     * @param bucketName
     * @param objectName
     * @return
     */
    public InputStream downloadFile(String bucketName, String objectName) throws IOException {
        try {
            MinioClient minioClient = minioConfig.getMinioClient();
            return minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(objectName).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteBucket(String bucket) throws Exception {
        minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucket).build());
    }

    public void deleteObject(String bucket, String objectName) throws Exception {
        minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucket).object(objectName).build());
    }
}
