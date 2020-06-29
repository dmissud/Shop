package org.dbs.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@ComponentScan("org.dbs.shop")
public class SpringBootInfrastuctureTest {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootInfrastuctureTest.class, args);
    }
}
