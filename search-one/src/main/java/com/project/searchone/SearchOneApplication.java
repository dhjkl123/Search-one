package com.project.searchone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// @EnableJpaRepositories(basePackages = {"com.project.searchone.repository"})
// @EntityScan(basePackages = {"com.project.searchone.dto"})
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
//@SpringBootApplication()
public class SearchOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchOneApplication.class, args);
    }

}
