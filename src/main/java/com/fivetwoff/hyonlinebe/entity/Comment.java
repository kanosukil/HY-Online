package com.fivetwoff.hyonlinebe.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author VHBin
 * @date 2021/12/20 - 14:45
 */

@Component
@Data
public class Comment {
    private Integer id;
    private String content;
}
