package com.fivetwoff.hyonlinebe.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author VHBin
 * @date 2021/12/27 - 10:01
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenStatus implements Serializable {
    private static final long serialVersionUID = 1L;

    private Object data;
    private String msg;

}
