package com.tber.anzk.thread;

import com.tber.anzk.lock.entity.IDistributeLock;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * @author jianglu email:ansndx@163.com
 */
@Slf4j
public class FairThreadDemo implements Runnable {

    private final IDistributeLock lock;
    private final CountDownLatch latch;

    public FairThreadDemo(IDistributeLock lock, CountDownLatch latch) {
        this.lock = lock;
        this.latch = latch;
    }


    @Override
    public void run() {
        if(lock.acquire()){
            log.info(Thread.currentThread().getName()+" 获取到了锁....ok");

                log.info(Thread.currentThread().getName()+" 正在你处理业务....");
                log.info(Thread.currentThread().getName()+" 业务处理完成....ok");

            lock.release();
            log.info(Thread.currentThread().getName()+" 释放了锁 ok...");
        }

        latch.countDown();
    }
}
