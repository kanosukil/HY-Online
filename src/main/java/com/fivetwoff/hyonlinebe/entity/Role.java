package com.fivetwoff.hyonlinebe.entity;

import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * @author VHBin
 * @date 2021/12/20 - 14:52
 */

@Getter
@ToString
@Component
public class Role {
    private Integer id;
    private String rank;
}
