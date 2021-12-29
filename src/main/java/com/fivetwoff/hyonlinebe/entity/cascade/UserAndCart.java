package com.fivetwoff.hyonlinebe.entity.cascade;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author VHBin
 * @date 2021/12/20 - 20:34
 */

@Data
@Component
public class UserAndCart {
    private Integer user_key;
    private Integer cart_key;
}
