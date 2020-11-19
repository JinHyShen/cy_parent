package com.hong.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.hong"})
public class EduAppcalition {
    public static void main(String[] args) {
        SpringApplication.run ( EduAppcalition.class,args );
    }
}
