package com.fivetwoff.hyonlinebe.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author VHBin
 * @date 2021/12/27 - 10:46
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreDTO {
    private String storeName;
    private Integer userKey;
}
