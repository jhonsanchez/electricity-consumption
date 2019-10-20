package com.zenhome.assignment.electricityconsumption;

import com.zenhome.assignment.electricityconsumption.entity.jpa.CounterConsumptionJpaFactory;
import com.zenhome.assignment.electricityconsumption.entity.jpa.CounterConsumptionRepositoryJpa;
import com.zenhome.assignment.electricityconsumption.entity.property.InfraDbProperties;
import com.zenhome.assignment.electricityconsumption.property.RunnerProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties({InfraDbProperties.class, RunnerProperties.class})
public class Runner extends SpringBootServletInitializer {
    private final CounterConsumptionRepositoryJpa counterConsumptionRepositoryJpa;
    private final RunnerProperties runnerProperties;

    public Runner(CounterConsumptionRepositoryJpa counterConsumptionRepositoryJpa,
                  RunnerProperties runnerProperties) {
        this.counterConsumptionRepositoryJpa = counterConsumptionRepositoryJpa;
        this.runnerProperties = runnerProperties;
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
            if (runnerProperties.isPopulateData())
                counterConsumptionRepositoryJpa.saveAll(CounterConsumptionJpaFactory.createCounterConsumptionDemo());
        };
    }

}
