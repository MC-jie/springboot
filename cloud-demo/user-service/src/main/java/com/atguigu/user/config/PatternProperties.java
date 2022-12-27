package com.atguigu.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Mr jie
 * @create 2022-44-28-17:44
 */
@Component
@Data
@ConfigurationProperties(prefix = "info")
public class PatternProperties {
    public String version;
    private String envSharedValue;
    private String name;
}
