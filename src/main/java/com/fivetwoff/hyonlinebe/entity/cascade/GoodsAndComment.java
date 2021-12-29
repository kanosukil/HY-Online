package com.fivetwoff.hyonlinebe.entity.cascade;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author VHBin
 * @date 2021/12/27 - 09:28
 */

@Data
@Component
public class GoodsAndComment {
    private Integer goods_key;
    private Integer comment_key;
}
