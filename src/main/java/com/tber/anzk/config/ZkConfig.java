package com.tber.anzk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jianglu email:ansndx@163.com
 */
@Configuration
public class ZkConfig {

    private ZkProperties zkProperties;

    @Autowired
    public ZkConfig(ZkProperties zkProperties) {
        this.zkProperties = zkProperties;
    }

    @Bean(initMethod = "init")
    public ZkCenter initZkCenter(){
        return new ZkCenter(zkProperties);
    }
}
