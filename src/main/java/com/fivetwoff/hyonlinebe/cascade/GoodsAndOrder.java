package com.fivetwoff.hyonlinebe.cascade;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author VHBin
 * @date 2021/12/20 - 20:33
 */

@Data
@Component
public class GoodsAndOrder {
    private Integer goods_key;
    private Integer order_key;
}
