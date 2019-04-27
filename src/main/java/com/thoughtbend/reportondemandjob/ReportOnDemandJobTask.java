package com.thoughtbend.reportondemandjob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.task.listener.TaskExecutionListener;
import org.springframework.cloud.task.listener.annotation.AfterTask;
import org.springframework.cloud.task.listener.annotation.BeforeTask;
import org.springframework.cloud.task.listener.annotation.FailedTask;
import org.springframework.cloud.task.repository.TaskExecution;

public class ReportOnDemandJobTask implements CommandLineRunner, TaskExecutionListener {

	private static final Logger LOG = LoggerFactory.getLogger(ReportOnDemandJobTask.class);
	
	@Override
	public void run(String... args) throws Exception {
		
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

	@BeforeTask
	@Override
	public void onTaskStartup(TaskExecution taskExecution) {
		taskExecution.setExternalExecutionId("-1");
		taskExecution.setParentExecutionId(-2L);
		LOG.info("onTaskStartup - " + taskExecution);
	}

	@AfterTask
	@Override
	public void onTaskEnd(TaskExecution taskExecution) {
		taskExecution.setExitMessage("Finished in good order!");
		taskExecution.setExternalExecutionId("-1");
		taskExecution.setParentExecutionId(-2L);
		LOG.info("onTaskEnd - " + taskExecution);
	}

	@FailedTask
	@Override
	public void onTaskFailed(TaskExecution taskExecution, Throwable throwable) {
		LOG.info("onTaskFailed - " + taskExecution);
	}

}
