package com.hong.eduservice.controller;


import com.hong.commonUtils.JsonResult;
import com.hong.eduservice.entity.EduVideo;
import com.hong.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/edu/video")
@CrossOrigin
public class EduVideoController {
    @Autowired
    private EduVideoService eduVideoService;

    //添加小节
    @PostMapping("/addVideo")
    public JsonResult addVideo(@RequestBody  EduVideo eduVideo){
        System.out.println ("eduVideo"+eduVideo);
        eduVideoService.save ( eduVideo );
        return JsonResult.success ();

    }

    //删除小节
    //TODO 后面这个方法需要完善，删除小节的时候需要把视频删除
    @DeleteMapping("delete/{videoId}")
    public JsonResult deleteVideo(@PathVariable String videoId){
        eduVideoService.removeById ( videoId );
        return JsonResult.success ();
    }

    //修改小节 TODO
    @PostMapping("update/video")
    public JsonResult updateVideo(@RequestBody  EduVideo eduVideo){
        eduVideoService.saveOrUpdate(eduVideo);
        return JsonResult.success ();

    }
    //查询小节
    @GetMapping("find/{videoId}")
    public JsonResult selectVideo(@PathVariable String videoId ){
        EduVideo video = eduVideoService.getById ( videoId );
        return JsonResult.success ().data ( "video",video );
    }
}

