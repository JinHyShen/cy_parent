package com.hong.commonUtils;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class JsonResult {

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "状态码")
    private Integer code;

    @ApiModelProperty(value = "返回信息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String,Object> data = new HashMap<> ();

    private JsonResult(){ }

    public static JsonResult success(){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setSuccess ( true );
        jsonResult.setCode ( ResultCode.SUCCESS );
        jsonResult.setMessage ( "操作成功..." );
        return jsonResult;
    }


    public static JsonResult error(){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setSuccess ( false );
        jsonResult.setCode ( ResultCode.ERROR );
        jsonResult.setMessage ( "操作失败..." );
        return jsonResult;
    }

    public JsonResult success(Boolean success){
        this.setSuccess ( success );
        return this;
    }

    public JsonResult message(String message){
        this.setMessage ( message );
        return this;
    }

    public JsonResult code(Integer code){
        this.setCode ( code );
        return this;
    }

    public JsonResult data(String key,Object value){
        this.data.put ( key,value );
        return this;
    }

    public JsonResult data(Map<String,Object> map){
        this.setData ( map );
        return this;
    }
}
