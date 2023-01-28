package com.zitao.sentinel.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> {
    private String message;
    private Integer code;

    /**
     * false: 失败，true: 成功
     */
    private Boolean status;

    private T data;

    public CommonResult(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public CommonResult(String message, Integer code, Boolean status) {
        this.message = message;
        this.code = code;
        this.status = status;
    }

    public static CommonResult error(String message, Integer code) {
        return new CommonResult(message, code, false);
    }

    public static CommonResult success(String message, Integer code) {
        return new CommonResult(message, code, true);
    }
}
