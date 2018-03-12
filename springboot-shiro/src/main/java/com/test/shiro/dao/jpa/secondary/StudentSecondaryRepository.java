package com.test.shiro.dao.jpa.secondary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.shiro.bean.po.Student;

@Repository
public interface StudentSecondaryRepository extends JpaRepository<Student, Long>  {

    
    
}
