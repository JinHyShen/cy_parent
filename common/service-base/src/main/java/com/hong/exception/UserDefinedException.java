package com.hong.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自定义异常处理
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDefinedException extends RuntimeException{

    private Integer code;
    private String msg;

}
