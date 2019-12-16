package com.yangyou.upload.util;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.ObjectMetadata;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * This is Description
 *
 * @author yang
 * @date 2019/12/10
 */

public class OssUtil {

    //这里的配置可以在properties或者yml中进行配置
    @Value("${oss.accessKeyId}")
    private String accessKeyId;

    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${oss.bucketName}")
    private String bucketName;

    @Value("${oss.endPoint}")
    private String endPoint;

    @Value("${oss.picLocation}")
    private String picLocation;

    //文件直接上传的方式，filename 为定义的文件名字
    public void upload(File obj,String fileType) {
        String fileName = picLocation + UUID.randomUUID().toString().toUpperCase().replace("-", "")+"."+fileType;
        OSSClient ossClient = null;
        try {
            ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);
            ossClient.putObject(bucketName, fileName, obj);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ossClient != null)
                ossClient.shutdown();
        }
    }


    //文件字节方式进行上传，filename 为定义的文件名字
    public void upload(byte[] array, String fileName) {
        long start = System.currentTimeMillis();
        OSSClient ossClient = null;
        try {
            ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);
            ossClient.putObject(bucketName, fileName, new ByteArrayInputStream(array));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ossClient != null)
                ossClient.shutdown();

        }
    }

    /**
     * 功能描述:
     *
     * @param:[objectKey, multipartFile 文件的新名称]
     * @return:java.lang.String
     * @date:2018/10/14 15:46
     **/
    public String uploadFile(String fileType, MultipartFile multipartFile)
            throws OSSException, ClientException, FileNotFoundException {

        // 创建OSSClient的实例
        OSSClient ossClient  = new OSSClient(endPoint,accessKeyId,accessKeySecret);

        if (multipartFile.getSize() != 0 && !"".equals(multipartFile.getName())) {
            ObjectMetadata om = new ObjectMetadata();
            om.setContentLength(multipartFile.getSize());
            fileType = picLocation+ UUID.randomUUID().toString().toUpperCase().replace("-", "")+"."+fileType;
            // 设置文件上传到服务器的名称
            om.addUserMetadata("filename", fileType);
            try {
                ossClient.putObject(bucketName, fileType, new ByteArrayInputStream(multipartFile.getBytes()), om);
            } catch (IOException e) {

            }
        }
        // 设置这个文件地址的有效时间
        Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365);
        String url = ossClient.generatePresignedUrl(bucketName, fileType, expiration).toString();
        System.out.println("url = " + url);
        return url;
    }
}