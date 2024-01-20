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
    private final int status;
    /**
     * 错误信息描述
     */
    private final String message;

    public BusinessException(int status) {
        this.status = status;
        //todo msg对应表
        this.message = "未知错误";
    }
    
    public BusinessException(int status, String message) {
        
        super(status + "-" + message);
        this.status = status;
        this.message = message;
    }
    
    public int getStatus() {
        return status;
    }
    
    public String getMessage() {
        return message;
    }
}
