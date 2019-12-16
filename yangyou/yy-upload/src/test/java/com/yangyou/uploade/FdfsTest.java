package com.yangyou.uploade;

import com.aliyun.oss.internal.OSSUploadOperation;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.domain.ThumbImageConfig;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.yangyou.upload.LyUploadService;
import com.yangyou.upload.controller.UploadController;
import com.yangyou.upload.util.OSSUploadUtil;
import com.yangyou.upload.util.OssUtil;
import org.apache.http.entity.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This is Description
 *
 * @author yang
 * @date 2019/12/10
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LyUploadService.class)
public class FdfsTest {

    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private ThumbImageConfig thumbImageConfig;
    @Autowired
    OssUtil ossUtil;


    @Test
    public void testUpload() throws FileNotFoundException {
        File file = new File("/Users/yang/Pictures/down/2019-09-15/1568512396340.jpg");

        FileInputStream fileInputStream = new FileInputStream(file);
        MultipartFile multipartFile = null;
        try {
            multipartFile = new MockMultipartFile("copy"+file.getName(),file.getName(), ContentType.APPLICATION_OCTET_STREAM.toString(),fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String type = multipartFile.getContentType();
        String url = ossUtil.uploadFile(type, multipartFile);
        System.out.println("str = " + url);

    }

    public static void main(String[] args) {
        String endpoint = "";
    }


}