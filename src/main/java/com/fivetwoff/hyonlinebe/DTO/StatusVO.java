package com.fivetwoff.hyonlinebe.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author VHBin
 * @date 2021/12/27 - 21:52
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusVO {
    private Integer code;
    private Boolean status;
}
