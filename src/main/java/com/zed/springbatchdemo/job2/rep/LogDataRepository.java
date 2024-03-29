package com.zed.springbatchdemo.job2.rep;

import com.zed.springbatchdemo.job2.model.LogData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author Zeluo
 * @date 2019/9/24 10:31
 */
@Repository
public interface LogDataRepository extends JpaRepository<LogData,Integer> {

}
