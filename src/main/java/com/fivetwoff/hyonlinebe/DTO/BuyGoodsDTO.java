package com.fivetwoff.hyonlinebe.DTO;

import lombok.Data;

import java.util.List;

/**
 * @author VHBin
 * @date 2021/12/30 - 21:56
 */

@Data
public class BuyGoodsDTO {
    private Integer uid;
    private List<Integer> gids;
}
