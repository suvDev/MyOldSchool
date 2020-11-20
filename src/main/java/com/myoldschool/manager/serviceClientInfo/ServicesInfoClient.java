package com.myoldschool.manager.serviceClientInfo;

import com.myoldschool.manager.studentnames.StudentNameCountClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableDiscoveryClient
@RestController
public class ServicesInfoClient {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "serviceInfo");
        SpringApplication.run(StudentNameCountClient.class, args);
    }

    @GetMapping("/getInfo")
    public String getServiceEndPoints(){
        return "all endpoints";
    }

}
