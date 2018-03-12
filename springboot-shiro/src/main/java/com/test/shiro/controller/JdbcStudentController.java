package com.test.shiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.shiro.bean.common.JsonResult;
import com.test.shiro.bean.po.Student;
import com.test.shiro.service.JdbcStudentService;

@RestController
@RequestMapping("/jdbc/student")
public class JdbcStudentController {
    
    @Autowired
    private JdbcStudentService studentService;
    
    @RequestMapping(method=RequestMethod.GET,value="/getStudent")
    public JsonResult<Student> getStudent() {
	JsonResult<Student> jsonResult = new JsonResult<Student>();
	jsonResult.setData(studentService.getOne());
	return jsonResult;
    }
    
    @RequestMapping(method=RequestMethod.POST,value="/addStudent")
    public JsonResult<Integer> addStudent() {
	JsonResult<Integer> jsonResult = new JsonResult<Integer>();
	jsonResult.setData(studentService.addStudent());
	return jsonResult;
    }
    
}
