package com.fivetwoff.hyonlinebe.DTO;

import com.fivetwoff.hyonlinebe.entity.Goods;
import lombok.Data;

@Data
public class SubGoods {
    private String storeName;
    private Goods goods;
}
