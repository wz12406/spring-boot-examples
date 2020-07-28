package com.springcloud.springcloudopenfeign.model;

/**
 * Created by jiaoyefei
 * <p>
 * 普惠返回值配置
 */
public enum PHResultCodeEnum {

    SUCCESS("000", "操作成功"),;

    PHResultCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
