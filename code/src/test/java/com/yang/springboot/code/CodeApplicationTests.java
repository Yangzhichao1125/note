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
        String filePath = fastDFSClient.uploadFile("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1568264934065&di=3db5c8e6a1e4f3ee1a1dc5912b00010c&imgtype=0&src=http%3A%2F%2Fpic002.cnblogs.com%2Fimages%2F2012%2F360373%2F2012081915433774.jpg");
        System.out.println("返回路径：" + filePath);

        photoDao.addPhoto(filePath,2);


    }
}
