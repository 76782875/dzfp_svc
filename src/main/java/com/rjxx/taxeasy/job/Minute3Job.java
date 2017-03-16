package com.rjxx.taxeasy.job;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xlm on 2017/3/14.
 */
public class Minute3Job implements Job {
    private Logger logger= LoggerFactory.getLogger(Minute3Job.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("JobName3: {}", context.getJobDetail().getKey().getName());
    }

}
