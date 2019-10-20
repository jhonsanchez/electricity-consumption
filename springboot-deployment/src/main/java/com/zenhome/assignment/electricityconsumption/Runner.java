package com.zenhome.assignment.electricityconsumption;

import com.zenhome.assignment.electricityconsumption.entity.jpa.CounterConsumptionJpaFactory;
import com.zenhome.assignment.electricityconsumption.entity.jpa.CounterConsumptionRepositoryJpa;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Runner extends SpringBootServletInitializer {
    private final CounterConsumptionRepositoryJpa counterConsumptionRepositoryJpa;

    public Runner(CounterConsumptionRepositoryJpa counterConsumptionRepositoryJpa) {
        this.counterConsumptionRepositoryJpa = counterConsumptionRepositoryJpa;
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Runner.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Runner.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            counterConsumptionRepositoryJpa.saveAll(CounterConsumptionJpaFactory.createCounterConsumptionDemo());
        };
    }

}
