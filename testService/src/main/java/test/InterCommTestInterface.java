package test;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "inter-comm")
public interface InterCommTestInterface {

    @GetMapping(path = "/testInterComm")
    String testInterServiceCommunication();

}
