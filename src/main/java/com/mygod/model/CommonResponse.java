package com.mygod.model;

/**
 * Created by legolas on 2016/1/8.
 */
public class CommonResponse {
    private int code;
    private String message;
    private String extra;

    public CommonResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {

        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
