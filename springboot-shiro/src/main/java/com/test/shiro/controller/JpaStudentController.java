package com.test.shiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.shiro.bean.common.JsonResult;
import com.test.shiro.bean.po.Student;
import com.test.shiro.service.JpaStudentService;

@RestController
@RequestMapping("/jpa/student")
public class JpaStudentController {

    @Autowired
    private JpaStudentService jpaStudentService;
    
    @RequestMapping(method=RequestMethod.GET,value="/getStudent")
    public JsonResult<Student> getStudent(long id) {
	JsonResult<Student> jsonResult = new JsonResult<Student>();
	jsonResult.setData(jpaStudentService.getOne(id));
	return jsonResult;
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/addStudent")
    public JsonResult<Integer> addStudent() {
	JsonResult<Integer> jsonResult = new JsonResult<Integer>();
	jsonResult.setData(jpaStudentService.addStudent());
	return jsonResult;
    }
}
