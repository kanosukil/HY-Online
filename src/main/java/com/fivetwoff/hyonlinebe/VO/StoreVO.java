package com.fivetwoff.hyonlinebe.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author VHBin
 * @date 2021/12/27 - 10:52
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreVO {
    private Integer code;
    private Boolean toOpenStore = false;
    private String info;
}
