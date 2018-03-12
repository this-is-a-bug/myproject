package com.test.shiro.dao.jdbc.primary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentPrimaryDao {

    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    
    public int addStudent() {
	int res = jdbcTemplate.update("insert into student(name,password) values(?,?)", "张三","123456");
	logger.info("插入成功条数:{}",res);
	return res;
    }
    
    
}
