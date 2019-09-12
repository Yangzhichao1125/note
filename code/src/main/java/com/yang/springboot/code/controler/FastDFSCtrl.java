package com.yang.springboot.code.controler;

import com.yang.springboot.code.util.FastDFSClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * This is Description
 *
 * @author yang
 * @date 2019/09/11
 */
@RestController
public class FastDFSCtrl {



    @RequestMapping(value = "file/uploadFast",method = RequestMethod.GET)
    public String uploadFast(HttpServletRequest request)throws Exception {
        // 1、把FastDFS提供的jar包添加到工程中
        // 2、初始化全局配置。加载一个配置文件。
        String confUrl = this.getClass().getClassLoader().getResource("/fdfs_client.properties").getPath();
        FastDFSClient fastDFSClient = new FastDFSClient(confUrl);
        //上传文件
        String filePath = fastDFSClient.uploadFile("/Users/yang/Pictures/4.png");
        System.out.println("返回路径：" + filePath);
        return "返回路径：" + filePath;
    }
}
