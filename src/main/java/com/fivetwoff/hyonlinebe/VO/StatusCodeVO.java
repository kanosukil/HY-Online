package com.fivetwoff.hyonlinebe.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author VHBin
 * @date 2021/12/27 - 14:24
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusCodeVO {
    private Integer code;
    private Object info;
}
