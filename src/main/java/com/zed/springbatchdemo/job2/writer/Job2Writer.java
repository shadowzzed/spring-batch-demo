package com.zed.springbatchdemo.job2.writer;

import com.zed.springbatchdemo.job2.model.LogData;
import com.zed.springbatchdemo.job2.rep.LogDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import sun.rmi.runtime.Log;

import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * @author Zeluo
 * @date 2019/9/24 9:57
 */
@Component
@Slf4j
public class Job2Writer {
    @Autowired
    private EntityManagerFactory entityManagerFactory;
    @Autowired
    private LogDataRepository repository;

    private static JpaItemWriter<LogData> writer = new JpaItemWriter<>();
//    @Override
//    public void write(List<? extends LogData> items) {
//        for (LogData item: items)
//            repository.save(item);
//    }

    @Bean
    public JpaItemWriter<LogData> logWriter() {
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }

}
