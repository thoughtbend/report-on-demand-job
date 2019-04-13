package com.thoughtbend.reportondemandjob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReportOnDemandJobApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(ReportOnDemandJobApplication.class);
	
	public void run(String... args) {
		final long startTimestamp = System.currentTimeMillis();
		LOG.info("Hello Job Runner - let's sleep a bit");
		try {
			long time = 10000L;
			if (args.length > 0 && args[0] != null) {
				time = Long.parseLong(args[0]);
			}
			LOG.info(String.format("Sleeping for %1$sms", time));
			Thread.sleep(time);
		} catch (InterruptedException e) {
			LOG.error(e.getMessage(), e);
		}
		LOG.info(String.format("Goodbye Job Runner - you took %1$sms", (System.currentTimeMillis() - startTimestamp)));
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ReportOnDemandJobApplication.class, args);
	}

}
