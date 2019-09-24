package com.zed.springbatchdemo.job2.writer;

import com.zed.springbatchdemo.job2.model.LogData;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Iterator;
import java.util.List;

/**
 * @author Zeluo
 * @date 2019/9/24 15:15
 */
@Component
public class Job2HandleWriter implements ItemWriter<LogData[]>, InitializingBean {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    private int count = 0;
    @Override
    public void write(List<? extends LogData[]> list) throws Exception {
        if (entityManagerFactory == null) {
            System.out.println(count++);
            return;
        }
        EntityManager entityManager = EntityManagerFactoryUtils.getTransactionalEntityManager(entityManagerFactory);
        if (entityManager == null) {
            throw new Exception("Error");
        }
        doWrite(entityManager, list.get(0));
        entityManager.flush();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(entityManagerFactory, "An EntityManagerFactory is required");
    }

    protected void doWrite(EntityManager entityManager, LogData[] items) {
        if (items.length > 0) {
            long mergeCount = 0;
            for (LogData item : items) {
                if (!entityManager.contains(item)) {
                    entityManager.merge(item);
                    mergeCount++;
                }
            }
        }
    }
}
