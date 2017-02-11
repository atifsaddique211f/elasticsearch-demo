package com.aurora;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.PostConstruct;
import javax.inject.Inject;


@SpringBootApplication
public class Application
{
    @Inject
    private Bootstrap bootstrap;

    public static void main(String[] args)
    {
        new SpringApplicationBuilder(Application.class)
                .profiles("dev")
                .run(args);
    }

    @PostConstruct
    public void initApplication()
    {
        bootstrap.init();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer()
    {
        return new WebMvcConfigurerAdapter()
        {
            @Override
            public void addCorsMappings(CorsRegistry registry)
            {
                registry.addMapping("/api/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "OPTIONS");
            }
        };
    }
}
