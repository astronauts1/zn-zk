package com.tber.anzk.lock.entity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

/**
 * @author jianglu email:ansndx@163.com
 */
@Slf4j
@RequiredArgsConstructor
public class ZkDistributedLockImpl implements IDistributeLock{
    /**
     * 锁实体
     */
    private final InterProcessMutex lock;


    @Override
    public boolean acquire(){
        try {
            lock.acquire();
            return true;
        } catch (Exception e) {
            log.error("锁获取异常!!");
            return false;
        }
    }

    @Override
    public boolean release(){
        try {
            lock.release();
            return true;
        } catch (Exception e) {
            log.error("锁释放异常!");
            return false;
        }
    }
}
