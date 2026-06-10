package com.pysarivka.WeekEnds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class WeekEndsApplication extends SpringBootServletInitializer { // ОБЯЗАТЕЛЬНО НАСЛЕДУЕМСЯ

    @Override
    protected org.springframework.boot.builder.SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WeekEndsApplication.class); // Нужно для развертывания WAR
    }

    public static void main(String[] args) {
        SpringApplication.run(WeekEndsApplication.class, args);
    }
}
