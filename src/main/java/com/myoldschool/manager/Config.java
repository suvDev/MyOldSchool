package com.myoldschool.manager;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "com.myoldschool.manager")
@PropertySource("classpath:data")
public class Config {
}
