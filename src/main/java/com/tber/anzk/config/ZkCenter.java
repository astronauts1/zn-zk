package com.tber.anzk.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author jianglu email:ansndx@163.com
 */
@Slf4j
public class ZkCenter {

    private ZkProperties zkProperties;


    public ZkCenter(ZkProperties zkProperties) {
        this.zkProperties = zkProperties;
    }

    @Getter
    private CuratorFramework zkClient;

    public void init(){
        zkClient = CuratorFrameworkFactory.builder()
                .connectString(zkProperties.getHost())
                .connectionTimeoutMs(zkProperties.getTimeout())
                .namespace(zkProperties.getNamespace())
                .retryPolicy(new ExponentialBackoffRetry(zkProperties.getBaseSleepMs()
                        , zkProperties.getRetryTimes())).build();

        zkClient.start();

        log.info("zookeeper connect success!!");
    }

}
