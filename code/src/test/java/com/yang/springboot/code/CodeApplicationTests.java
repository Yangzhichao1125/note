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

        FastDFSClient fastDFSClient = new FastDFSClient("classpath:fdfs_client.properties");

        String path = "/Users/yang/Pictures/down/2019-09-15/";		//要遍历的路径
        File file = new File(path);		                            //获取其file对象
        File[] fs = file.listFiles();
        int success = 0;
        int faile = 0;
        for (File f:fs ) {
            String filePath = fastDFSClient.uploadFile(path+f.getName());
            if (filePath != null) {
                photoDao.addPhoto(filePath,1);
                success++;
            }else {
                faile ++;
            }
        }
        System.out.println("success = " + success);
        System.out.println("faile = " + faile);


    }
}
