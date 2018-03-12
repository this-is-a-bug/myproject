package com.test.mybatis.config;

import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.apache.bcel.util.ClassLoaderRepository;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@AutoConfigureAfter({DataSourceConfig.class})
public class MybatisConfiguration extends MybatisAutoConfiguration {

    private static Logger logger = LoggerFactory.getLogger(MybatisConfiguration.class);
    
    @Autowired
    @Qualifier("primaryDataSource")
    private DataSource primaryDataSource;
    
    @Autowired
    @Qualifier("secondaryDataSource")
    private DataSource secondaryDataSource;
    
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        return super.sqlSessionFactory(roundRobinDataSouceProxy());
    }
    
    @Bean
    public PlatformTransactionManager transactionManager(
	    @Qualifier("primaryDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    private AbstractRoutingDataSource roundRobinDataSouceProxy(){
	AbstractRoutingDataSource proxy = new ReadWriteSplitRoutingDataSource();
        Map<Object,Object> targetDataResources = new ClassLoaderRepository.SoftHashMap();
        targetDataResources.put(DbContextHolder.DbType.MASTER,primaryDataSource);
        targetDataResources.put(DbContextHolder.DbType.SLAVE,secondaryDataSource);
        proxy.setDefaultTargetDataSource(primaryDataSource);//默认源
        proxy.setTargetDataSources(targetDataResources);
        return proxy;
    }

}
