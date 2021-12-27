package com.fivetwoff.hyonlinebe.config;

import lombok.Data;

/**
 * @author VHBin
 * @date 2021/12/27 - 12:17
 */

@Data
public class SystemStatus {
    public static final Boolean ON = true;
    public static final Boolean OFF = false;
    private Boolean status = ON;
}
