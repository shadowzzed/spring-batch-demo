package com.zed.springbatchdemo.job2;

import com.zed.springbatchdemo.job2.listener.Job2Listener;
import com.zed.springbatchdemo.job2.model.LogData;
import com.zed.springbatchdemo.job2.processor.Job2Processor;
import com.zed.springbatchdemo.job2.reader.DataAnaylzeReader;
import com.zed.springbatchdemo.job2.writer.Job2Writer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Zeluo
 * @date 2019/9/24 9:28
 */
@Configuration
public class Job2Config {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job processJob2(@Qualifier("orderStep2") Step orderStep) {
        return jobBuilderFactory.get("dataAnalyze")
                .incrementer(new RunIdIncrementer())
                .listener(new Job2Listener())
                .flow(orderStep)
                .end()
                .build();
    }
    @Bean
    public Step orderStep2(@Qualifier("logWriter") JpaItemWriter<LogData> logDataJpaItemWriter) {
        return stepBuilderFactory.get("dataAnalyzeStep1")
                .<String, LogData>chunk(50)
                .reader(new DataAnaylzeReader())
                .processor(new Job2Processor())
                .writer(logDataJpaItemWriter)
                .build();
    }
}
