package com.neo.model;

import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author wangzhen
 * @since 2020/07/01 21:24
 */
@Setter
@ToString
public class PHCommonRespDTO<T> implements Serializable {

    private static final long serialVersionUID = 2311034651344835755L;
    private String code;
    private String message;
    private T result;

    PHCommonRespDTO() {

    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public PHCommonRespDTO(String ret_code, String ret_info, T data) {
        this.code = ret_code;
        this.message = ret_info;
        this.result = data;
    }

    private PHCommonRespDTO(String ret_code, String ret_info) {
        super();
        this.code = ret_code;
        this.message = ret_info;
    }

    public boolean isSuccess() {
        return this.code.equals(PHResultCodeEnum.SUCCESS.getCode());
    }

    public T getResult() {
      return this.result;
    }

}
