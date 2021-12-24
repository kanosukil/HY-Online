package com.fivetwoff.hyonlinebe.cascade;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author VHBin
 * @date 2021/12/20 - 20:40
 */

@Data
@Component
public class UserAndStore {
    private Integer master_key;
    private Integer store_key;
}
