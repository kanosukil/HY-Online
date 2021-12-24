package com.fivetwoff.hyonlinebe.cascade;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author VHBin
 * @date 2021/12/20 - 20:26
 */

@Data
@Component
public class GoodsAndCart {
    private Integer goods_key;
    private Integer cart_key;
}
