package com.fivetwoff.hyonlinebe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author VHBin
 * @date 2021/12/27 - 12:19
 */

@Configuration
public class InitialConfig {
    @Bean
    public SystemStatus systemStatus() {
        return new SystemStatus();
    }
}
