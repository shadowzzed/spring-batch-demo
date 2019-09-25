package com.zed.springbatchdemo.job2.model;

import lombok.Data;
import org.springframework.core.annotation.Order;

import javax.persistence.*;

/**
 * @author Zeluo
 * @date 2019/9/24 9:29
 */
@Entity
@Data
@Table(name = "log_data")
@Order(1)
public class LogData {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String requestId;
    private String requestParams;
    private String responseParams;
}
