package com.amr.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication(exclude = {SessionAutoConfiguration.class,
                                  SecurityAutoConfiguration.class})
@EnableSwagger2
public class ProjectApplication {
    public static void main(String[] args){
        SpringApplication.run(ProjectApplication.class, args);
    }
}
