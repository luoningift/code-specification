package cn.datafort.exception;

import cn.datafort.constant.RepStatusEnum;
import cn.datafort.constant.RepVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public RepVo<Object> exceptionHandler(Exception e) {

        // http 请求方式错误
        if (e instanceof HttpRequestMethodNotSupportedException) {
            return new RepVo<>(RepStatusEnum.OTHER.getStatus(), "请求方式错误");
        }

        // 参数类型错误
        if (e instanceof TypeMismatchException) {
            return new RepVo<>(RepStatusEnum.PARAM_CHECK_FAIL.getStatus(), "参数类型错误");
        }

        // json 格式错误
        if (e instanceof HttpMessageNotReadableException) {
            return new RepVo<>(RepStatusEnum.PARAM_CHECK_FAIL.getStatus(), "json格式错误");
        }

        // 参数校验未通过
        if (e instanceof MethodArgumentNotValidException) {
            return new RepVo<>(RepStatusEnum.PARAM_CHECK_FAIL.getStatus(), e.getMessage());
        }

        if (e instanceof MissingServletRequestParameterException) {
            return new RepVo<>(RepStatusEnum.PARAM_CHECK_FAIL.getStatus(), "缺少请求参数");
        }

        // 处理GET请求中使用 @valid 验证路径中请求实体校验失败后抛出的异常
        if (e instanceof BindException) {
            return new RepVo<>(RepStatusEnum.PARAM_CHECK_FAIL.getStatus(), "未知错误");
        }

        // 数字异常
        if (e instanceof NumberFormatException) {
            return new RepVo<>(RepStatusEnum.PARAM_CHECK_FAIL.getStatus(), "格式化数字错误");
        }

        // 业务报错
        if (e instanceof BusinessException) {
            final BusinessException businessException = (BusinessException) e;
            return new RepVo<>(businessException.getStatus(), businessException.getMessage());
        }
        // 处理请求参数格式错误：@RequestParam 上失败后抛出的异常
        if (e instanceof ConstraintViolationException) {
            return new RepVo<>(RepStatusEnum.PARAM_CHECK_FAIL.getStatus(), e.getMessage());
        }
        
        if (e instanceof ValidationException) {
            final Throwable cause = e.getCause();
            if (cause instanceof BusinessException) {
                final BusinessException businessException = (BusinessException) cause;
                return new RepVo<>(businessException.getStatus(), businessException.getMessage());
            }
        }
        
        return new RepVo<>(RepStatusEnum.OTHER.getStatus(), "系统异常");
    }
}
