package com.myoldschool.manager.zuulservice;

import com.myoldschool.manager.ManagerApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class MyZuulProxyClient {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "zuulService");
        SpringApplication.run(ManagerApplication.class, args);
    }
}
