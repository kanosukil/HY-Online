package com.fivetwoff.hyonlinebe.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author VHBin
 * @date 2021/12/20 - 14:46
 */

@Data
@Component
public class Goods {
    private Integer id;
    private String name;
    private String img;
    private Double price;
    private String description;
}
