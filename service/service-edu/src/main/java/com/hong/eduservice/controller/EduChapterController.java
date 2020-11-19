package com.hong.eduservice.controller;


import com.hong.commonUtils.JsonResult;
import com.hong.eduservice.entity.EduChapter;
import com.hong.eduservice.entity.chapter.ChapterVO;
import com.hong.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/edu/chapter")
@CrossOrigin
public class EduChapterController {

    @Autowired
    private EduChapterService eduChapterService;
    //课程大纲列表,根据课程id进行查询
    @GetMapping("/getChapter/{courseId}")
    public JsonResult chapterVideo(@PathVariable String courseId){
        List<ChapterVO> list =eduChapterService.getChapterVideoByCourseId(courseId);
        return JsonResult.success ().data("chapters",list);
    }
    //添加章节
    @PostMapping("/addChapter")
    public JsonResult addChapterInfo(@RequestBody EduChapter eduChapter){
        System.out.println ("------222");
        eduChapterService.save ( eduChapter );
        return JsonResult.success ();
    }
    //根据章节id查询
    @GetMapping("/findChapterInfo/{chapterId}")
    public JsonResult selectChapterInfo(@PathVariable String chapterId){
        EduChapter eduChapter = eduChapterService.getById ( chapterId );
        return JsonResult.success ().data ( "chapter",eduChapter );
    }
    //修改章节
    @PostMapping("/editChapter")
    public JsonResult updateChapterInfo(@RequestBody EduChapter eduChapter){
        eduChapterService.saveOrUpdate ( eduChapter );
        return JsonResult.success ();
    }
    //删除章节
    @DeleteMapping("/delete/{chapterId}")
    public JsonResult deleteChapter(@PathVariable String chapterId){
        boolean flag = eduChapterService.deleteChapter ( chapterId );
        if(flag){
            return JsonResult.success ();
        }else {
            return JsonResult.error ();
        }

    }
}

