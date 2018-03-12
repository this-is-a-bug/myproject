package com.test.mybatis.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.mybatis.bean.common.JsonResult;
import com.test.mybatis.bean.po.Student;
import com.test.mybatis.service.StudentService;

@RestController
@RequestMapping("/mybatis/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    
    @RequestMapping("/getAll")
    public JsonResult<List<Student>> getAll() {
	JsonResult<List<Student>> res = new JsonResult<>();
	res.setData(studentService.getAll(0, 4));
	return res;
    }
    
    @RequestMapping("/getStudentSubjectScore")
    public JsonResult<List<Map<String, Object>>> getStudentSubjectScore() {
	JsonResult<List<Map<String, Object>>> res = new JsonResult<>();
	res.setData(studentService.getStudentSubjectScore());
	return res;
    }
    
    @RequestMapping("/getRedisKey1")
    public JsonResult<Object> getRedisKey1() {
	JsonResult<Object> res = new JsonResult<>();
	System.out.println("进入接口1");
	res.setData(studentService.seckillBook(0));
	return res;
    }
    
    @RequestMapping("/getRedisKey2")
    public JsonResult<Object> getRedisKey2() {
	JsonResult<Object> res = new JsonResult<>();
	System.out.println("进入接口2");
	res.setData(studentService.seckillBook(0));
	return res;
    }
    
    @RequestMapping("/getRedisKey3")
    public JsonResult<Object> getRedisKey3() {
	JsonResult<Object> res = new JsonResult<>();
	System.out.println("进入接口3");
	res.setData(studentService.seckillBook(0));
	return res;
    }
    
}
