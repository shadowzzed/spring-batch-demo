package com.zed.springbatchdemo.job2.writer;

import com.zed.springbatchdemo.job2.model.LogData;
import com.zed.springbatchdemo.job2.rep.LogDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Zeluo
 * @date 2019/9/25 13:51
 */
@Component
@Slf4j
@Order(3)
public class Job2Writer2 implements ItemWriter<LogData[]> {

    private LogDataRepository repository;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public Job2Writer2(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final String INSERT_SQL = "INSERT INTO log_data (request_id,request_params,response_params) values (?,?,?)";

    @Override
    public void write(List<? extends LogData[]> items) throws Exception {
        log.info("插入数据************************************");
        LogData[] logDatas = items.get(0);
        for (LogData logData: logDatas) {
            log.info(logData.toString());
            int update = jdbcTemplate.update(INSERT_SQL, logData.getRequestId(), logData.getRequestParams(), logData.getResponseParams());
            log.info("{}",update);
        }
    }
}
