package io.haicheng.dashu.domain.vo;


import java.io.Serializable;
import lombok.Data;

/**
 */
@Data
public class ServiceVO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int SUCCESS_CODE = 100;
    public static final String SUCCESS_MESS = "请求成功";

    public static final int FAIL_CODE = 200;
    public static final String FAIL_MESS = "请求失败";

    private int code;
    private String msg;
    private T info;

    public ServiceVO() {
        this.code = SUCCESS_CODE;
        this.msg = SUCCESS_MESS;
    }

    public ServiceVO(T info) {
        this.code = SUCCESS_CODE;
        this.msg = SUCCESS_MESS;
        this.info = info;
    }

    public ServiceVO(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ServiceVO(int code, String msg, T info) {
        this.code = code;
        this.msg = msg;
        this.info = info;
    }

}