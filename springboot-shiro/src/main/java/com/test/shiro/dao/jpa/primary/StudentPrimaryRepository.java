package com.test.shiro.dao.jpa.primary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.shiro.bean.po.Student;

@Repository
public interface StudentPrimaryRepository extends JpaRepository<Student, Long> {

}
