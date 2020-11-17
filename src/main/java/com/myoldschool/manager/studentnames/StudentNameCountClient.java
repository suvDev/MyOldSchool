package com.myoldschool.manager.studentnames;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableDiscoveryClient
@RestController
public class StudentNameCountClient {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "studentcount");
        SpringApplication.run(StudentNameCountClient.class, args);
    }

}
