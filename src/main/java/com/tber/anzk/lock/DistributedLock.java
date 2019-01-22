package com.tber.anzk.lock;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

/**
 * @author jianglu email:ansndx@163.com
 */
@Slf4j
@RequiredArgsConstructor
public class DistributedLock {
    /**
     * 锁实体
     */
    private final InterProcessMutex lock;


    public void acquire(){
        try {
            lock.acquire();
        } catch (Exception e) {
            log.error("锁获取异常!!");
        }
    }

    public void release(){
        try {
            lock.release();
        } catch (Exception e) {
            log.error("锁释放异常!");
        }
    }
}
