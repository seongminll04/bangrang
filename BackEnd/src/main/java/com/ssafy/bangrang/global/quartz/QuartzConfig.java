package com.ssafy.bangrang.global.quartz;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail helloWorldJobDetail() {
        return JobBuilder.newJob(HelloWorldJob.class)
                .withIdentity("helloWorldJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger helloWorldTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(helloWorldJobDetail())
                .withIdentity("helloWorldTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("*/10 * * * * ?")) // 매 10초마다
                .build();
    }
}
