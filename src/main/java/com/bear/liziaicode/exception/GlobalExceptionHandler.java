package com.bear.liziaicode.exception;

import com.bear.liziaicode.common.BaseResponse;
import com.bear.liziaicode.common.ResultUtils;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//为了防止意料之外的异常，利用AOP切面全局对业务异常和RuntimeException进行捕获：
@Hidden
@RestControllerAdvice
@Slf4j
//由于本项目使用的SpringBoot版本>=3.4、并且是OpenAPI 3版本的Knife4j，这会导致@RestControllerAdvice
//注解不兼容，所以必须给这个类加上@Hidden 注解，不被Swagger加载。虽然上也有其他的解决案，但这种法是最直接有效的
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e) {
        log.error("BusinessException", e);
        return ResultUtils.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException", e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "系统错误");
    }
}
