package com.fivetwoff.hyonlinebe.cascade;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author VHBin
 * @date 2021/12/20 - 20:27
 */

@Data
@Component
public class StoreAndGoods {
    private Integer store_key;
    private Integer goods_key;
}
