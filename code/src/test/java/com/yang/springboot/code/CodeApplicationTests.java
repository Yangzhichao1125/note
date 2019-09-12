package com.yang.springboot.code;

import com.apple.eio.FileManager;
import com.yang.springboot.code.dao.PhotoDao;
import com.yang.springboot.code.dao.TbuserDao;
import com.yang.springboot.code.util.FastDFSClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CodeApplicationTests {

    @Autowired
    TbuserDao tbuserDao;

    @Autowired
    PhotoDao photoDao;


    @Test
    public void contextLoads() {
        System.out.println(tbuserDao.getAll());
    }


    @Test
    public void upload() throws Exception {

//        String confUrl=this.getClass().getClassLoader().getResource("/fdfs_client.properties").getPath();

        FastDFSClient fastDFSClient = new FastDFSClient("classpath:fdfs_client.properties");
        //上传文件
        String filePath = fastDFSClient.uploadFile("");
        System.out.println("返回路径：" + filePath);

        photoDao.addPhoto(filePath,2);


    }
}
