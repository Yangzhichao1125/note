package com.yangyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * This is Description
 *
 * @author yang
 * @date 2019/12/09
 */
@SpringBootApplication
@EnableEurekaServer
public class Registry {
    public static void main(String[] args) {
        SpringApplication.run(Registry.class);
    }
}
