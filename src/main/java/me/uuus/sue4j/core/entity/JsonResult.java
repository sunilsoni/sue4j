package me.uuus.sue4j.core.entity;

/**
 * @author Mr.Su[swb0917@gmail.com]
 * @since 2016/7/17 19:41
 */
public class JsonResult<T> extends Result {

    private static final long serialVersionUID = -7183254406673263485L;

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public JsonResult() {
        super();
    }

    public JsonResult(boolean success, String message) {
        super.setSuccess(success);
        super.setMessage(message);
    }

    public JsonResult(T data, String message, boolean success) {
        this.data = data;
        super.setMessage(message);
        super.setSuccess(success);
    }

    /**
     * 默认成功后返回的数据和消息
     * @param data 数据
     * @param message 消息
     */
    public JsonResult(T data, String message) {
        this.data = data;
        super.setMessage(message);
        super.setSuccess(true);
    }

    /**
     * 默认返回成功的数据
     * @param data 数据
     */
    public JsonResult(T data) {
        this.data = data;
        super.setSuccess(true);
    }

}
