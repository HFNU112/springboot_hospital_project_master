package com.lb.vo;

import lombok.Data;

/**
 * @author 蓝莲花
 * @version 1.0.0
 * @ClassName ResponseResult.java
 * @Description TODO
 * @createTime 2020年03月25日 18:16:00
 */
@Data
public class ResponseResult {
    private String code;
    private String message;
    private String data;

    public ResponseResult() {}

    public ResponseResult(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
