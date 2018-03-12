package com.test.mybatis.bean.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.test.mybatis.dao.base.BaseEntity;


@Entity
@Table(name="student")
public class Student extends BaseEntity {

    @Column(name="name")
    private String name;
    private String password;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    @Override
    public String toString() {
	return "Student [id=" + getId() + ", name=" + name + ", password="
		+ password + "]";
    }
}
