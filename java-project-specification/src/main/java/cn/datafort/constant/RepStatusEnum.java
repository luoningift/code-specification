package cn.datafort.constant;

public enum RepStatusEnum {
    
    SUCCESS(1000, "访问返回成功"),
    POWER_USER_STATUS(1999, "当前用户状态未启用"),
    POWER_MODE_GRANT_ERROR(2000, "模块授权错误"),
    POWER_USER_LOWER(2001, "用户权限不足错误"),
    POWER_USER_IDERROR(2002, "身份识别错误 需要登陆"),
    POWER_USER_PASSWORD_TIMEOUT(2003, "密码过期"),
    PARAM_CHECK_FAIL(3000, "参数校验不通过错误"),
    SERVICE_ERROR(6000, "第三方错误"),
    DATA_ERROR(4000, "数据错误"),
    OTHER(7000, "其他错误");
    
    private final int status;
    private final String message;
    
    RepStatusEnum(int code, String desc) {
        this.status = code;
        this.message = desc;
    }
    
    public String getMessage() {
        return message;
    }
    
    public int getStatus() {
        return status;
    }
}

