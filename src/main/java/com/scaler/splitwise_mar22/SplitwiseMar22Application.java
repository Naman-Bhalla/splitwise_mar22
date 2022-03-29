package com.scaler.splitwise_mar22;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SplitwiseMar22Application {

    public static void main(String[] args) {
        SpringApplication.run(SplitwiseMar22Application.class, args);
    }

}
