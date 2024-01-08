package cn.datafort.exception;

import lombok.Getter;

/**
 * 业务异常
 *
 * @author 胡鑫虎
 * @version 1.0
 * @date 2022/12/2 15:53
 */
@Getter
public class BusinessException extends RuntimeException {

    /**
     * 异常错误码
     */
    private final int code;
    /**
     * 错误信息描述
     */
    private final String msg;

    public BusinessException(int code) {
        this.code = code;
        //todo msg对应表
        this.msg = "未知错误";
    }
    
    public BusinessException(int code, String msg) {
        
        super(code + "-" + msg);
        this.code = code;
        this.msg = msg;
    }
    
    public int getCode() {
        return code;
    }
    
    public String getMsg() {
        return msg;
    }
}
