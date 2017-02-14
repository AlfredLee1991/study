package com.framework.validate.result;

import java.io.Serializable;

/**
 * 功能描述：校验结果抽象.<br/>
 * 
 * #date： 2016年10月10日 下午3:12:12<br/>
 * #author 8104485-李旭<br/>
 * #since 1.0.0<br/>
 */
public class ValidateResult implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -8857650475433931338L;

    /**
     * 验证场景类型
     */
    private String type;

    /**
     * 校验结果编码
     */
    private String code;

    /**
     * 校验结果描述
     */
    private String message;

    /**
     * 校验异常
     */
    private Throwable error;

    /**
     * 
     */
    public ValidateResult() {
        super();
    }

    /**
     * @param type
     * @param code
     * @param message
     * @param error
     */
    public ValidateResult(String type, String code, String message, Throwable error) {
        super();
        this.type = type;
        this.code = code;
        this.message = message;
        this.error = error;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }

}
