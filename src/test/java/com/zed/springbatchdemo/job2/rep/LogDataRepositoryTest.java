package com.zed.springbatchdemo.job2.rep;

import com.zed.springbatchdemo.job2.model.LogData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author Zeluo
 * @date 2019/9/24 10:36
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LogDataRepositoryTest {
    @Autowired
    LogDataRepository repository;
    @Test
    public void test() {
        LogData logData = new LogData();
        logData.setRequestId("1");
        logData.setResponseParams("1");
        logData.setRequestParams("1");

        LogData save = repository.save(logData);
    }

}