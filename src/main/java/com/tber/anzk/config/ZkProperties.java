package com.tber.anzk.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author jianglu email:ansndx@163.com
 */
@ConfigurationProperties(prefix = "zookeeper")
@Component
@Data
public class ZkProperties {
    private String host;
    private String namespace;
    private Integer timeout;

    private Integer baseSleepMs;

    private Integer retryTimes;
}
