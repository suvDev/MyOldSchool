package com.myeurekaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableEurekaClient
@RestController
public class EurekaClientManager {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "accounts-server");
        SpringApplication.run(EurekaClientManager.class, args);
    }

    @RequestMapping("/")
    public String testEureka(){
        return "eureka client running";
    }
}
