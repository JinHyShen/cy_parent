package com.hong.vod.controller;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.hong.commonUtils.JsonResult;
import com.hong.exception.UserDefinedException;
import com.hong.vod.service.VodService;
import com.hong.vod.utils.ConstantVodUtil;
import com.hong.vod.utils.InitClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/vod")
@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;

    //上传视频的方法(上传都用post)
    @PostMapping("/uploadVideo")
    public JsonResult uploadVideoToAliYun(MultipartFile file ){
        //返回上传视频id
        String videoId = vodService.uploadVideo(file);
        return JsonResult.success ().data ( "videoId",videoId );

    }
    //删除视频
    @DeleteMapping("removeVod/{id}")
    public JsonResult deleteVideo(@PathVariable String id){
        try {
            //初始化对象
            DefaultAcsClient client = InitClient.initVodClient ( ConstantVodUtil.ACCESS_KEY_ID, ConstantVodUtil.ACCESS_KEY_SECRET );
            //创建删除视频对象
            DeleteVideoRequest request = new DeleteVideoRequest ();
            request.setVideoIds ( id );
            client.getAcsResponse ( request );
            return JsonResult.success ();
        }catch (Exception e){
            e.printStackTrace ();
            throw new UserDefinedException ( 20001,"操作失败" );
        }

    }
}
