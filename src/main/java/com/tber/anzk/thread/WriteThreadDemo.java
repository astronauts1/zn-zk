package com.tber.anzk.thread;

import com.tber.anzk.lock.entity.IDistributeLock;
import com.tber.anzk.lock.entity.ZkDistributedLockImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * @author jianglu email:ansndx@163.com
 */
@Slf4j
public class WriteThreadDemo implements Runnable{

    private final IDistributeLock lock;

    private final CountDownLatch latch;

    public WriteThreadDemo(ZkDistributedLockImpl lock, CountDownLatch latch) {
        this.lock = lock;
        this.latch = latch;
    }




    @Override
    public void run() {

        lock.acquire();
        log.info(Thread.currentThread().getName()+" write write write....ok");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.release();
        log.info(Thread.currentThread().getName()+" write release ok...");

        latch.countDown();
    }
}
