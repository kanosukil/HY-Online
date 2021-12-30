package com.fivetwoff.hyonlinebe.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author VHBin
 * @date 2021/12/30 - 00:19
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartGoodsVO {
    private Integer id;
    private String storeName;
    private String goodsSrc;
    private String goodsTitle;
    private String goodsSubtitle;
    private String goodsPrice;
    private Integer goodsNum;
}
