package com.yangyou.item;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;


/**
 * This is Description
 *
 * @author yang
 * @date 2019/12/09
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.yangyou.item.mapper") // mapper接口的包扫描
public class YyItemService {
    public static void main(String[] args) {
        SpringApplication.run(YyItemService.class);
    }
}
