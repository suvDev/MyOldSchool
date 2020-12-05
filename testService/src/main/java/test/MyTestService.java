package test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableEurekaClient
@RestController
@EnableFeignClients
public class MyTestService {

    @Autowired
    InterCommTestInterface interCommTestInterface;

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application");
        SpringApplication.run(MyTestService.class, args);
    }

    @GetMapping("/myTestService")
    public String getServiceEndPoints(){
        return interCommTestInterface.testInterServiceCommunication()+"service running";
    }
}
