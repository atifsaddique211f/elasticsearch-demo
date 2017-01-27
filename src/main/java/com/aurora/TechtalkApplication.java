package com.aurora;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication
public class TechtalkApplication
{

    public static void main(String[] args)
    {
        new SpringApplicationBuilder(TechtalkApplication.class)
                .profiles("dev")
                .run(args);
    }
}
