package com.hong.exception;

import com.hong.commonUtils.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class EduExceptionHandler {

    /**
     * 全局异常处理
     * @param e
     * @return
     */
    //指定出现什么异常执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody//为了返回数据
    public JsonResult handleException(Exception e){
            e.printStackTrace ();
        return JsonResult.error ().message ( "处理异常..." );
    }


    /**
     * 特定异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody//为了返回数据
    public JsonResult handleException(ArithmeticException e){
        e.printStackTrace ();
        return JsonResult.error ().message ( "特定处理异常..." );
    }

    /**
     * 自定义异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(UserDefinedException.class)
    @ResponseBody//为了返回数据
    public JsonResult handleException(UserDefinedException e){
        log.error ( e.getMessage () );
        e.printStackTrace ();
        return JsonResult.error ().code ( e.getCode () ).message ( e.getMsg () );
    }
}
