package com.tber.anzk;

import com.tber.anzk.lock.DistributedLock;
import com.tber.anzk.lock.IDistributedLockFacade;
import com.tber.anzk.thread.ReadThreadDemo;
import com.tber.anzk.thread.WriteThreadDemo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

/**
 * @author jianglu email:ansndx@163.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ZkLockTests {


    @Autowired
    IDistributedLockFacade distributedLockFacade;

    @Test
    public void zkTest() {
        DistributedLock readLock = distributedLockFacade.getReadLock("demo");

        DistributedLock writeLock = distributedLockFacade.getWriteLock("demo");

        final CountDownLatch latch = new CountDownLatch(3);


        Thread  readThreadDemo1 = new Thread(new ReadThreadDemo(readLock,latch),"read 111");

        Thread  readThreadDemo2 = new Thread(new ReadThreadDemo(readLock,latch),"read 222");

        Thread writeThreadDemo = new Thread(new WriteThreadDemo(writeLock,latch),"write 111");
        readThreadDemo1.start();
        writeThreadDemo.start();
        readThreadDemo2.start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
