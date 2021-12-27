package com.fivetwoff.hyonlinebe.login;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author VHBin
 * @date 2021/12/27 - 08:25
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {
    private Integer code;
    private Integer userId;
    private Boolean isAdmin = false;
    private T data;

    public Result(Integer code, Integer userId, T data) {
        this.code = code;
        this.userId = userId;
        this.data = data;
    }

    public Result(Integer code, Integer userId, Boolean isAdmin, T data) {
        this.code = code;
        this.userId = userId;
        this.isAdmin = isAdmin;
        this.data = data;
    }

    public Result() {
    }
}
