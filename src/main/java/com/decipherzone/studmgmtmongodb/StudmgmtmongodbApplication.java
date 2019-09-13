package com.decipherzone.studmgmtmongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "com.decipherzone")
public class StudmgmtmongodbApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(StudmgmtmongodbApplication.class, args);
    }
}