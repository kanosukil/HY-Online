package com.fivetwoff.hyonlinebe.cascade;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author VHBin
 * @date 2021/12/20 - 20:33
 */

@Data
@Component
public class StoreAndOrder {
    private Integer store_key;
    private Integer order_key;
}
