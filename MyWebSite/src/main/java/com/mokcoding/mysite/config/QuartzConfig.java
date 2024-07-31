package com.mokcoding.mysite.config;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mokcoding.mysite.job.AttachJob;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail attachJobDetail() {
        return JobBuilder.newJob(AttachJob.class)
                .withIdentity("attachJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger attachJobTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(attachJobDetail())
                .withIdentity("attachJobTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 3 * * ?")) // 매일 3시에 실행
                .build();
    }
}
