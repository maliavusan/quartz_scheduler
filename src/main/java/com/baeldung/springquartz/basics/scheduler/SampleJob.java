package com.baeldung.springquartz.basics.scheduler;

import com.baeldung.springquartz.basics.service.SampleJobService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SampleJob implements Job {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SampleJobService jobService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        logger.info("Job ** {} ** fired @ {}", context.getJobDetail().getKey().getName(), context.getFireTime());

        // Call market create and Send notification for market monitors
        jobService.executeSampleJob();

        logger.info("Next job scheduled @ {}", context.getNextFireTime());
    }
}
