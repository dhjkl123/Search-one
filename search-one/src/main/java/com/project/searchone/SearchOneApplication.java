package com.project.searchone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class SearchOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchOneApplication.class, args);
    }

}
