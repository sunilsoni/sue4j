package me.uuus.sue4j.core.entity;

import java.io.Serializable;

/**
 * @author Mr.Su[swb0917@gmail.com]
 * @since 2016/7/17 19:35
 */
public class Result implements Serializable {

    private static final long serialVersionUID = 7108642139378144124L;

    private String message;

    private int statusCode;

    private boolean success;

    public Result() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
