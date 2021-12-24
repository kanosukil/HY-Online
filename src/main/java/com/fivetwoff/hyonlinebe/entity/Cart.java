package com.fivetwoff.hyonlinebe.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author VHBin
 * @date 2021/12/20 - 16:20
 */

@Data
@Component
public class Cart {
    private Integer id;
    private Double total_price;
}
