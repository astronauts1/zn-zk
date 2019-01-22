package com.tber.anzk.lock;

/**
 * @author jianglu email:ansndx@163.com
 */
public interface IDistributedLockFacade {

    /**
     * 获取读锁
     * @param businessId 业务
     * @return 锁
     */
    DistributedLock getReadLock(String businessId);

    /**
     * 获取写锁
     * @param businessId 业务
     * @return 锁
     */
    DistributedLock getWriteLock(String businessId);


}
