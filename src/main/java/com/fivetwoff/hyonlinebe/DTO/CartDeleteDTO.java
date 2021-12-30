package com.fivetwoff.hyonlinebe.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author VHBin
 * @date 2021/12/30 - 16:32
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDeleteDTO {
    private Integer gid;
    private Integer uid;
}
