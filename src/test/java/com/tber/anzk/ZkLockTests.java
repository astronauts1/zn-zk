package com.tber.anzk;

import com.tber.anzk.lock.entity.IDistributeLock;
import com.tber.anzk.lock.entity.RedisDistributedLockImpl;
import com.tber.anzk.thread.FairThreadDemo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

/**
 * @author jianglu email:ansndx@163.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ZkLockTests {


//    @Autowired
//    IDistributedLockFacade distributedLockFacade;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Test
    public void zkTest() {
//        ZkDistributedLockImpl readLock = distributedLockFacade.getReadLock("demo");
//
//        ZkDistributedLockImpl writeLock = distributedLockFacade.getWriteLock("demo");


        IDistributeLock redisLock = new RedisDistributedLockImpl(redisTemplate,"redis_lock",30000L);

        final CountDownLatch latch = new CountDownLatch(3);


        Thread  readThreadDemo1 = new Thread(new FairThreadDemo(redisLock,latch),"redis 111");

        Thread  readThreadDemo2 = new Thread(new FairThreadDemo(redisLock,latch),"redis 222");


        Thread  readThreadDemo3 = new Thread(new FairThreadDemo(redisLock,latch),"redis 333");

        readThreadDemo1.start();
        readThreadDemo2.start();
        readThreadDemo3.start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
