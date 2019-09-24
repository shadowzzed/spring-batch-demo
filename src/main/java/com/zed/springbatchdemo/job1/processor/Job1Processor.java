package com.zed.springbatchdemo.job1.processor;

import org.springframework.batch.item.ItemProcessor;

/**
 * @author Zeluo
 * @date 2019/9/24 9:03
 */
public class Job1Processor implements ItemProcessor<String,String> {

    @Override
    public String process(String s) throws Exception {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            return s.toUpperCase();
        }
        return null;
    }
}
