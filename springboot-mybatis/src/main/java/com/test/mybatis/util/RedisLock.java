package com.test.mybatis.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class RedisLock {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisLock.class);

    private static final int DEFAULT_LOCK_EXPIRSE_MILL_SECONDS = 1000 * 30;

    private static final int DEFAULT_LOCK_WAIT_DEFAULT_TIME_OUT = 1000 * 10;

    private static final int DEFAULT_LOOP_WAIT_TIME = 1;

    private boolean lock = false;

    private String lockKey = null;

    private int lockExpriseTimeout;

    private int lockWaitTimeOut;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Deprecated
    public RedisLock() {
    }

    public RedisLock(String lockKey, int lockExpriseTimeout, int lockWaitTimeOut) {
	this.lockKey = lockKey;
	this.lockExpriseTimeout = lockExpriseTimeout;
	this.lockWaitTimeOut = lockWaitTimeOut;
    }

    private boolean putIfAbsent(String expirseTimeStr) {
	return this.stringRedisTemplate.opsForValue().setIfAbsent(this.lockKey,expirseTimeStr);
    }

    private String getAndSet(String expirseTimeStr) {
	return this.stringRedisTemplate.opsForValue().getAndSet(this.lockKey,expirseTimeStr);
    }

    public boolean lock() {
	int lockWaitMillSeconds = this.lockWaitTimeOut;
	String redis_value = String.valueOf(System.currentTimeMillis() + this.lockExpriseTimeout);
	while (lockWaitMillSeconds > 0) {
	    lock = this.putIfAbsent(redis_value);
	    System.out.println(">>>>>>>>>>>设置值："+redis_value);
	    System.out.println(">>>>>>>>>>>设置循环时间："+lockWaitMillSeconds);
	    if (lock) {
		System.out.println("成功获取锁1111。。。。");
		this.lock = true;
		return this.lock;
	    } else {
		System.out.println("获取锁失败。。。。");
		String oldRedisValue = this.stringRedisTemplate.opsForValue().get(this.lockKey);
		long currentTimeMillSeconds = System.currentTimeMillis();
		if (!StringUtils.isEmpty(oldRedisValue) && Long.parseLong(oldRedisValue) < currentTimeMillSeconds) {
		    String currentRedisValue = this.getAndSet(String.valueOf(currentTimeMillSeconds + this.lockExpriseTimeout));
		    if (oldRedisValue.equals(currentRedisValue)) {
			System.out.println("成功获取锁2222。。。。");
			this.lock = true;
			return this.lock;
		    }
		}
	    }
	    lockWaitMillSeconds -= DEFAULT_LOOP_WAIT_TIME;
	}
	return false;
    }

    public void unlock() {
	if (this.lock) {
	    this.stringRedisTemplate.delete(this.lockKey);
	    this.lock = false;
	}
    }

    public boolean isLock() {
	return lock;
    }

    public void setLock(boolean lock) {
	this.lock = lock;
    }

    public String getLockKey() {
	return lockKey;
    }

    public void setLockKey(String lockKey) {
	this.lockKey = lockKey;
    }

    public int getLockExpriseTimeout() {
	return lockExpriseTimeout;
    }

    public void setLockExpriseTimeout(int lockExpriseTimeout) {
	this.lockExpriseTimeout = lockExpriseTimeout;
    }

    public int getLockWaitTimeOut() {
	return lockWaitTimeOut;
    }

    public void setLockWaitTimeOut(int lockWaitTimeOut) {
	this.lockWaitTimeOut = lockWaitTimeOut;
    }

    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
	this.stringRedisTemplate = stringRedisTemplate;
    }
}
