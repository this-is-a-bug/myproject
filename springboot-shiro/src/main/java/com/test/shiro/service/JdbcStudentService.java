package com.test.shiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.shiro.bean.po.Student;
import com.test.shiro.dao.jdbc.primary.StudentPrimaryDao;
import com.test.shiro.dao.jdbc.secondary.StudentSecondaryDao;

@Service
public class JdbcStudentService {

    @Autowired
    private StudentPrimaryDao studentPrimaryDao;
    
    @Autowired
    private StudentSecondaryDao studentSecondaryDao;
    
    @Transactional(rollbackFor=Exception.class)
    public int addStudent() {
	int res = studentPrimaryDao.addStudent();
	System.out.println("插入结果为："+res);
	int a = 1 / 0;
	res = res + studentPrimaryDao.addStudent();
	System.out.println("插入结果为："+res);
	return res;
    }
    
    public Student getOne() {
	return studentSecondaryDao.getOne();
    }
    
    
}
