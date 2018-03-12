package com.test.mybatis.dao.mapper;

import java.util.List;
import java.util.Map;

import com.test.mybatis.bean.po.Student;
import com.test.mybatis.dao.base.BaseMapper;
public interface StudentMapper extends BaseMapper<Student> {

    //注解形式
    //@Select("SELECT a.id,a.`name`,b.`subject`,b.score FROM student a RIGHT JOIN subject_score b ON a.id = b.sid")
    List<Map<String, Object>> getStudentSubjectScore();
    
}
