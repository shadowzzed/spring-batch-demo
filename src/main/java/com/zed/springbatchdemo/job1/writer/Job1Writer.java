package com.zed.springbatchdemo.job1.writer;

import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * @author Zeluo
 * @date 2019/9/24 9:05
 */
public class Job1Writer implements ItemWriter<String> {

    @Override
    public void write(List<? extends String> list) throws Exception {
        for (String msg: list) {
            System.out.println("DATA: " + msg);
        }
    }
}
