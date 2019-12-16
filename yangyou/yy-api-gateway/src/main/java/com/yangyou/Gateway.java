package com.yangyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * This is Description
 *
 * @author yang
 * @date 2019/12/09
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class Gateway {
    public static void main(String[] args) {
        SpringApplication.run(Gateway.class);
    }
}
