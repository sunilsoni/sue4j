package me.uuus.sue4j.core.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mr.Su[swb0917@gmail.com]
 * @since 2016/7/17 19:47
 */
public class ErrorResult extends Result {

    private static final long serialVersionUID = -5482646300500148017L;

    private Map<String, Object> errors = new HashMap<>();

    public ErrorResult() {
    }

    public Map<String, Object> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, Object> errors) {
        this.errors = errors;
    }
}
