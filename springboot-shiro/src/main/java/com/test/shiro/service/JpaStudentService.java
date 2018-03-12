package com.test.shiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.shiro.bean.po.Student;
import com.test.shiro.dao.jpa.primary.StudentPrimaryRepository;
import com.test.shiro.dao.jpa.secondary.StudentSecondaryRepository;

@Service
public class JpaStudentService {

    @Autowired
    private StudentPrimaryRepository studentPrimaryRepository;
    
    @Autowired
    private StudentSecondaryRepository studentSecondaryRepository;
    
    @Transactional(rollbackFor=Exception.class)
    public int addStudent() {
	Student student = new Student();
	student.setName("李四1");
	student.setPassword("222222");
	Student student1 = studentPrimaryRepository.save(student);
	System.out.println("插入结果为："+student1);
	//int a = 1 / 0;测试事务
	return 1;
    }
    
    public Student getOne(long id) {
	return studentSecondaryRepository.findOne(id);
    }
    
    
}
