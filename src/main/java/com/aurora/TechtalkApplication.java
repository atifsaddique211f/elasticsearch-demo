package com.aurora;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.annotation.PostConstruct;
import javax.inject.Inject;


@SpringBootApplication
public class TechtalkApplication
{
    @Inject
    private Bootstrap bootstrap;

    public static void main(String[] args)
    {
        new SpringApplicationBuilder(TechtalkApplication.class)
                .profiles("dev")
                .run(args);
    }

    @PostConstruct
    public void initApplication()
    {
        bootstrap.init();
    }
}
