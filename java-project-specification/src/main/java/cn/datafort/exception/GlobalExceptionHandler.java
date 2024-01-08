package cn.datafort.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e) {

        // http 请求方式错误
        if (e instanceof HttpRequestMethodNotSupportedException) {
            return ResponseEntity.ok("请求方式错误");
        }

        // 参数类型错误
        if (e instanceof TypeMismatchException) {
            return ResponseEntity.ok("参数类型错误");
        }

        // json 格式错误
        if (e instanceof HttpMessageNotReadableException) {
            return ResponseEntity.ok("json格式错误");
        }

        // 参数校验未通过
        if (e instanceof MethodArgumentNotValidException) {
            return ResponseEntity.ok("参数校验未通过");
        }

        if (e instanceof MissingServletRequestParameterException) {
            return ResponseEntity.ok("缺少请求参数");
        }

        // 处理GET请求中使用 @valid 验证路径中请求实体校验失败后抛出的异常
        if (e instanceof BindException) {
            return ResponseEntity.ok("未知错误");
        }

        // 数字异常
        if (e instanceof NumberFormatException) {
            return ResponseEntity.ok("格式化数字错误");
        }

        // 业务报错
        if (e instanceof BusinessException) {
            return ResponseEntity.ok("业务错误");
        }

        // 处理请求参数格式错误：@RequestParam 上失败后抛出的异常
        if (e instanceof ConstraintViolationException) {
            return ResponseEntity.ok(e.getMessage());
        }
        return ResponseEntity.ok("请求参数格式错误");
    }
}
