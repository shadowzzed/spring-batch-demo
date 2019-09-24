package com.zed.springbatchdemo.job2.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Zeluo
 * @date 2019/9/24 9:29
 */
@Entity
@Data
public class LogData {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;
    @Column(name = "request_id",nullable = true)
    private String requestId;
    @Column(name = "request_params",nullable = true)
    private String requestParams;
    @Column
    private String responseParams;
}
