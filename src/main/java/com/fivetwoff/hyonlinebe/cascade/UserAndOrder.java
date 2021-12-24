package com.fivetwoff.hyonlinebe.cascade;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author VHBin
 * @date 2021/12/20 - 20:38
 */

@Data
@Component
public class UserAndOrder {
    private Integer customer_key;
    private Integer order_key;
}
