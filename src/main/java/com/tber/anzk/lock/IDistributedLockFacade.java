package com.tber.anzk.lock;

import com.tber.anzk.lock.entity.ZkDistributedLockImpl;

/**
 * @author jianglu email:ansndx@163.com
 */
public interface IDistributedLockFacade {

    /**
     * 获取读锁
     * @param businessId 业务
     * @return 锁
     */
    ZkDistributedLockImpl getReadLock(String businessId);

    /**
     * 获取写锁
     * @param businessId 业务
     * @return 锁
     */
    ZkDistributedLockImpl getWriteLock(String businessId);


}
