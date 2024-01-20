package cn.datafort.constant;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 交互返回vo
 *
 * @author root
 */
@ApiModel(value = "封装返回数据", description = "返回响应数据")
public class RepVo<T> {
    
    @ApiModelProperty(value = "响应状态", example = "状态码：1000 返回成功，1999 当前用户状态未启用，2000 模块授权失败， 2001 用户权限不足，2002 身份识别错误 需要登陆, 3000 参数校验不通过错误，4000 数据错误， 7000其他错误")
    public int status;
    
    @ApiModelProperty(value = "响应信息")
    public String message;
    
    @ApiModelProperty(value = "响应数据")
    public T data;
    
    public RepVo() {
        this.status = RepStatusEnum.SUCCESS.getStatus();
        this.message = "操作成功!";
    }
    
    public RepVo(T data) {
        this.status = RepStatusEnum.SUCCESS.getStatus();
        this.message = "操作成功!";
        this.data = data;
    }
    
    public RepVo(int status, String message) {
        this.status = status;
        this.message = message;
    }
    
    public RepVo(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
    
    public int getStatus() {
        return status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
    
    public static RepVo<Object> SUCCESS() {
        return new RepVo<>(RepStatusEnum.SUCCESS.getStatus(), "SUCCESS");
    }
    
    public static RepVo<Object> EXCEPTION(String message) {
        return new RepVo<>(RepStatusEnum.DATA_ERROR.getStatus(), message);
    }
    
}

