package com.pysarivka.WeekEnds.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Отримуємо абсолютний шлях до офіційного кореня вашого сервера Tomcat
        String catalinaHome = System.getProperty("catalina.base");
        if (catalinaHome == null) {
            catalinaHome = System.getProperty("user.dir");
        }
        
        // Формуємо точний шлях до папки uploads, де вже лежать ваші фотографії
        String uploadPath = catalinaHome + "/uploads/";
        
        // Наказуємо Tomcat ділитися файлами з цієї папки за посиланням /uploads/**
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:" + uploadPath);
    }
}
