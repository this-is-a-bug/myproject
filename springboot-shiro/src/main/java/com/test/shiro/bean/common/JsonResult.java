package com.test.shiro.bean.common;

public class JsonResult<T> {

    private T data;
    private String msg;
    private String code;
    
    public JsonResult() {
	this.code = "1";
	this.msg = "success";
    }

    public T getData() {
	return data;
    }

    public void setData(T data) {
	this.data = data;
    }

    public String getMsg() {
	return msg;
    }

    public void setMsg(String msg) {
	this.msg = msg;
    }

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }
}
