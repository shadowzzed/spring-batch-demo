package com.zed.springbatchdemo.job2.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zeluo
 * @date 2019/9/24 9:28
 */
public class DataAnaylzeReader implements ItemReader<String> {

    private static final String FILEPATH = "D:\\workspace-commons\\log_api.log";

    private int count = 0;
    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        String[] strings = this.readContentLine();
        if (count < strings.length)
            return strings[count++];
        else
            count = 0;
        return null;
    }

    private String[] readContentLine() throws IOException {
        List<String> list = new ArrayList<>();
        FileInputStream inputStream = new FileInputStream(FILEPATH);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String str = null;
        while ((str = bufferedReader.readLine()) != null) {
            list.add(str);
        }
        inputStream.close();
        bufferedReader.close();
        return list.toArray(new String[list.size()]);
    }
}
