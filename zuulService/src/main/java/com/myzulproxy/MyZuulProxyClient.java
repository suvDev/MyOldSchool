package com.myzulproxy;

import com.myzulproxy.zuulfilter.ZulErrorFilter;
import com.myzulproxy.zuulfilter.ZulPostFilter;
import com.myzulproxy.zuulfilter.ZulPreFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MyZuulProxyClient {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "zuulService");
        SpringApplication.run(MyZuulProxyClient.class, args);
    }

    @Bean
    public ZulPreFilter zulPreFilter() {
        return new ZulPreFilter();
    }

    @Bean
    public ZulPostFilter zulPostFilter() {
        return new ZulPostFilter();
    }

    @Bean
    public ZulErrorFilter zulErrorFilter() {
        return new ZulErrorFilter();
    }
}
