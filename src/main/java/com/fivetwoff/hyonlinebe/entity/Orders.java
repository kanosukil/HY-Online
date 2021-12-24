package com.fivetwoff.hyonlinebe.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author VHBin
 * @date 2021/12/20 - 14:50
 */

@Data
@Component
public class Orders {
    private Integer id;
    private Integer number;
    private String address;
}
