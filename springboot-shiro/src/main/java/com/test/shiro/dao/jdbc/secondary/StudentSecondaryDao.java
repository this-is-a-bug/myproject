package com.test.shiro.dao.jdbc.secondary;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.test.shiro.bean.po.Student;

@Repository
public class StudentSecondaryDao {

    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    
    public Student getOne() {
	List<Student> students = jdbcTemplate.query("select * from student limit 1", new BeanPropertyRowMapper<Student>(Student.class));
	if(!CollectionUtils.isEmpty(students)) {
	    logger.info("查询结果为：{}",students.get(0));
	    return students.get(0);
	}
	return null;
    }
}
