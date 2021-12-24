package com.fivetwoff.hyonlinebe.cascade;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author VHBin
 * @date 2021/12/20 - 20:39
 */

@Data
@Component
public class UserAndRole {
    private Integer user_key;
    private Integer role_key;
}
