package com.test.mybatis.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.test.mybatis.annotation.ReadOnlyConnection;
import com.test.mybatis.bean.po.Student;
import com.test.mybatis.dao.mapper.StudentMapper;
import com.test.mybatis.util.RedisLock;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;
    
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    @ReadOnlyConnection
    public List<Student> getAll(int page,int pageSize) {
	PageHelper.startPage(page, pageSize);
	return studentMapper.selectAll();
    }
    
    @ReadOnlyConnection
    public List<Map<String, Object>> getStudentSubjectScore() {
	return studentMapper.getStudentSubjectScore();
    }
    
    public boolean seckillBook(int bookId) {
	BoundHashOperations<String,Object,Object> hashOps = stringRedisTemplate.boundHashOps("product:");
	RedisLock redisLock = new RedisLock("key:", 6000, 1000);
	redisLock.setStringRedisTemplate(this.stringRedisTemplate);
	if(redisLock.lock()) {
	    hashOps.increment("storage_id1", -1);
	    hashOps.increment("storage_id2", 1);
	    redisLock.unlock();
	}
	return true;
    }
    
    /*private void rediscall() {
	SessionCkallback<Object> callback = new SessionCallback<Object>() {
	    @Override
	    public Object execute(RedisOperations arg0)
		    throws DataAccessException {
		arg0.multi();
		arg0.boundHashOps("product:").increment("storage_id1", -1);
		return arg0.exec();
	    }
	};
	Object execute = stringRedisTemplate.execute(callback);
	System.out.println(">>>>>>>>>>>>>>"+execute);
    }*/
}
