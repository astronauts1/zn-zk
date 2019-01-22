package com.tber.anzk.lock.impl;

import com.tber.anzk.config.ZkCenter;
import com.tber.anzk.lock.DistributedLock;
import com.tber.anzk.lock.IDistributedLockFacade;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author jianglu email:ansndx@163.com
 */
@Component
@Slf4j
public class ZkDistributeLockImpl implements IDistributedLockFacade {

    private static final String ZK_LOCK_PATH = "/zk-lock/";

    private ZkCenter zkCenter;

    @Autowired
    public ZkDistributeLockImpl(ZkCenter zkCenter) {
        this.zkCenter = zkCenter;
    }

    @Override
    public DistributedLock getReadLock(String businessId) {
        String path = ZK_LOCK_PATH + businessId;
        CuratorFramework zkClient = zkCenter.getZkClient();
        InterProcessMutex lock = new InterProcessReadWriteLock(zkClient,path+"/read-write-lock").readLock();
        log.info("创建锁完成");
        return new DistributedLock(lock);
    }

    @Override
    public DistributedLock getWriteLock(String businessId) {
        String path = ZK_LOCK_PATH + businessId;
        CuratorFramework zkClient = zkCenter.getZkClient();
        return new DistributedLock(new InterProcessReadWriteLock(zkClient,path+"/read-write-lock").writeLock());
    }
}
