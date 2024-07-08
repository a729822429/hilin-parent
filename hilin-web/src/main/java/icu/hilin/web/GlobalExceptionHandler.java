package icu.hilin.web;

import icu.hilin.core.entity.vo.ResponseEnum;
import icu.hilin.core.entity.vo.ResponseError;
import icu.hilin.core.entity.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 全局异常处理
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseVO<?> exceptionHandler(Exception e) {

        if (e instanceof ResponseError) {
            return ((ResponseError) e).getResponseVO();
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            return ResponseEnum.FAILED.msg("不支持" + ((HttpRequestMethodNotSupportedException) e).getMethod() + "请求方式");
        } else if (e instanceof NoHandlerFoundException) {
            return ResponseEnum.FAILED.msg("请求资源不存在");
        } else if (e instanceof MethodArgumentNotValidException) {
            return ResponseEnum.BAD_REQUEST.msg(((MethodArgumentNotValidException) e).getBindingResult().getAllErrors().get(0).getDefaultMessage());
        } else if (e instanceof NoResourceFoundException) {
            return ResponseEnum.NOT_FOUND.build();
        }
        log.info("系统异常", e);
        return ResponseEnum.FAILED.build();
    }
}
