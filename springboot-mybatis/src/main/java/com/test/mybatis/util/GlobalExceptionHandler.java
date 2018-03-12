package com.test.mybatis.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.mybatis.bean.common.JsonResult;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = IllegalStateException.class)
    @ResponseBody
    public JsonResult<String> jsonErrorHandler(HttpServletRequest req) throws Exception {
	JsonResult<String> r = new JsonResult<>();
        r.setMsg("请求参数错误");
        r.setCode("10002");
        r.setData("");
        return r;
    }
    
}
