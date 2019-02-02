package com.tber.anzk.lock.entity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author jianglu email:ansndx@163.com
 */
@Slf4j
public class RedisDistributedLockImpl implements IDistributeLock{

    private RedisTemplate<String,Object> redisTemplate;

    public RedisDistributedLockImpl(RedisTemplate<String, Object> redisTemplate, String key, long expireMills) {
        this.redisTemplate = redisTemplate;
        this.key = key;
        this.expireMills = expireMills;
    }

    private String key;
    private long expireMills;

    private String requestUUID;


    private static final long LOCK_TIMEOUT_MILLS = 4000L;

    private static final long LOCK_RETRY_INTERVAL_MILLS = 100L;

    private static final Long OPTION_SUCCESS = 1L;

    @Override
    public  boolean acquire(){
        long end = System.currentTimeMillis()+LOCK_TIMEOUT_MILLS;
        this.requestUUID = UUID.randomUUID().toString();
        while (System.currentTimeMillis()<end){
            log.info(Thread.currentThread().getName()+"system:"+System.currentTimeMillis()+"-----end:"+end);
            log.info(Thread.currentThread().getName()+"尝试获取redis锁!!!!");
            Boolean result = redisTemplate.opsForValue().setIfAbsent(key,requestUUID,expireMills, TimeUnit.MILLISECONDS);
            if(result!=null&&result){
                log.info(Thread.currentThread().getName()+"成功获取redis锁!!!!");
                return true;
            }
            try {
                log.info(Thread.currentThread().getName()+"等待{}再试!!!!",LOCK_RETRY_INTERVAL_MILLS);
                Thread.sleep(LOCK_RETRY_INTERVAL_MILLS);
            } catch (InterruptedException e) {
                log.error(Thread.currentThread().getName()+"线程中断异常");
                return false;
            }
        }
        return false;
    }

    @Override
    public  boolean release(){
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end ";
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>(script);
        redisScript.setResultType(Long.class);
        Object execute = redisTemplate.execute(redisScript,Collections.singletonList(key), requestUUID);
        log.info("execute result ----:"+execute);
        if(OPTION_SUCCESS.equals(execute)){
            log.info("清除成功!!!!!!!!!!");
            return true;
        }
        return false;
    }
}
