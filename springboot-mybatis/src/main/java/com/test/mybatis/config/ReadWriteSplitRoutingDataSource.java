package com.test.mybatis.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 
 * @ClassName: ReadWriteSplitRoutingDataSource 
 * @Description: (根据标识获取不同源) 
 * @author zhangzijie 
 * @date 2017年11月16日 下午2:36:55
 */
public class ReadWriteSplitRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DbContextHolder.getDbType();
    }
}
