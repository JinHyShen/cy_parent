package com.hong.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.hong.oss.service.OssService;
import com.hong.oss.utils.ConstantPropertiesUtils;
import lombok.val;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {

    /**
     * service:上传头像
     * @param file
     * @return
     */
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        String endpoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName =ConstantPropertiesUtils.BUCKET_NAME;

        try{
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 上传文件流。
            InputStream inputStream = file.getInputStream ();
            String filename = UUID.randomUUID ().toString ().replaceAll ( "-","" )+file.getOriginalFilename () ;
            //2020/6/27me.jpg
            String datePath=new DateTime().toString ("yyyy/MM/dd");
            filename =datePath+"/"+filename;
            //oss实现上传 1.BucketName 2.上传到oss的文件名称或路径 3.上传文件流
            ossClient.putObject(bucketName, filename, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();
            //返回文件上传路劲
            String url ="https://"+bucketName+"."+endpoint+"/"+filename;
            return url;

        }catch (Exception e){
            e.printStackTrace ();
            return null;
        }
    }
}
