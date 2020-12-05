package com.tservice2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@RestController
public class InterCommTestService {
    public static void main(String[] args) {
        SpringApplication.run(InterCommTestService.class, args);
    }

    @GetMapping("/testInterComm")
    public String testInterServiceCommunication(){
        return "intercommunication working between services";
    }
}
