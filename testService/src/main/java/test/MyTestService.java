package test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableEurekaClient
@RestController
public class MyTestService {
    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application");
        SpringApplication.run(MyTestService.class, args);
    }

    @GetMapping("/myTestService")
    public String getServiceEndPoints(){
        return "service running";
    }
}
