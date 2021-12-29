package com.fivetwoff.hyonlinebe.entity.cascade;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author VHBin
 * @date 2021/12/20 - 20:37
 */

@Data
@Component
public class UserAndComment {
    private Integer user_key;
    private Integer comment_key;
}
