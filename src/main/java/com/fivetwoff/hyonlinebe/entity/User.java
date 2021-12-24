package com.fivetwoff.hyonlinebe.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author VHBin
 * @date 2021/12/20 - 14:43
 */

@Data
@Component
public class User {
    private Integer id;
    private String username;
    private String password_hash;
    private String head_portrait;
}
