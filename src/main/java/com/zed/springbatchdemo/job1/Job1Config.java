package com.zed.springbatchdemo.job1;

import com.zed.springbatchdemo.job1.listener.Job1Listener;
import com.zed.springbatchdemo.job1.processor.Job1Processor;
import com.zed.springbatchdemo.job1.reader.Job1Reader;
import com.zed.springbatchdemo.job1.writer.Job1Writer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.Reader;

/**
 * @author Zeluo
 * @date 2019/9/23 16:26
 */
//@Configuration
public class Job1Config {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job processJob() {
        return jobBuilderFactory.get("job1")
                .incrementer(new RunIdIncrementer())
                .listener(listener())
                .flow(orderStep1())
                .end()
                .build();
    }

    @Bean
    public JobExecutionListener listener() {
        return new Job1Listener();
    }

    @Bean
    public Step orderStep1() {
        return stepBuilderFactory.get("orderStep1")
                .<String,String>chunk(1)
                .reader(new Job1Reader())
                .processor(new Job1Processor())
                .writer(new Job1Writer())
                .build();
    }
}
