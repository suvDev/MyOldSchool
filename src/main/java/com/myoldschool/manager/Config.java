package com.myoldschool.manager;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableCaching
@ComponentScan(basePackages = {"com.myoldschool.manager", "test"})
@PropertySource("classpath:data")
public class Config {
}
