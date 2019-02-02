package com.tber.anzk.lock.entity;

/**
 * @author jianglu email:ansndx@163.com
 */
public interface IDistributeLock {
    boolean acquire();

    boolean release();
}
