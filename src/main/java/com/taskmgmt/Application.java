package com.taskmgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class, RepositoryRestMvcAutoConfiguration.class})
public class Application {
    public static void main (String[] args){
        SpringApplication.run(Application.class);
    }
}
