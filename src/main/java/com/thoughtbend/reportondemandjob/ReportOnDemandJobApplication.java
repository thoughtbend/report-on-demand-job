package com.thoughtbend.reportondemandjob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.cloud.task.repository.TaskNameResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.config.EnableIntegration;

@SpringBootApplication
@EnableIntegration
@EnableTask
public class ReportOnDemandJobApplication {

	//private static final Logger LOG = LoggerFactory.getLogger(ReportOnDemandJobApplication.class);
	
	@Bean
	public CommandLineRunner commandLineRunner() {
		return new ReportOnDemandJobTask();
	}

	
	public static void main(String[] args) {
		SpringApplication.run(ReportOnDemandJobApplication.class, args);
	}

}
