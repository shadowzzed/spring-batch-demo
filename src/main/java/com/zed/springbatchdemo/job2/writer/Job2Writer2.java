package com.zed.springbatchdemo.job2.writer;

import com.zed.springbatchdemo.job2.model.LogData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Zeluo
 * @date 2019/9/25 13:51
 */
@Component
@Slf4j
public class Job2Writer2 implements ItemWriter<LogData[]> {


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public Job2Writer2(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final String INSERT_SQL = "INSERT INTO log_data (request_id,request_params,response_params) values (?,?,?)";

    private int count = 1;
    @Override
    public void write(List<? extends LogData[]> items) throws Exception {
        log.info("第{}次插入数据************************************",count++);
        LogData[] logDatas = items.get(0);
        jdbcTemplate.batchUpdate(INSERT_SQL, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setString(1,logDatas[i].getRequestId());
                preparedStatement.setString(2,logDatas[i].getRequestParams());
                preparedStatement.setString(3,logDatas[i].getResponseParams());
            }

            @Override
            public int getBatchSize() {
                return logDatas.length;
            }
        });
//        for (LogData logData: logDatas) {
//            log.info("第{}次运行write",count++);
//            jdbcTemplate.update(INSERT_SQL, logData.getRequestId(), logData.getRequestParams(), logData.getResponseParams());
////            repository.save(logData);
////            log.info("{}",update);
//        }
    }
}
