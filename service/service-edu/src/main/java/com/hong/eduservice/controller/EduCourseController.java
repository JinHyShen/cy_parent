package com.hong.eduservice.controller;


import com.hong.commonUtils.JsonResult;
import com.hong.eduservice.entity.EduCourse;
import com.hong.eduservice.entity.vo.CourseInfoVo;
import com.hong.eduservice.entity.vo.CoursePublishVo;
import com.hong.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/edu/course")
@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;

    //课程列表实现
    //TODO 完善条件查询带分页效果
    @GetMapping("/selectAll")
    public JsonResult listCourse(){
        List<EduCourse> courseList = eduCourseService.list ( null );
        return JsonResult.success ().data ( "courseList",courseList );

    }

    //添加课程信息
    @PostMapping("/addCourse")
    public JsonResult addCourse(@RequestBody CourseInfoVo courseInfoVo){
        //返回添加之后的课程id，未添加课程大纲使用
        String id =eduCourseService.saveCourse(courseInfoVo);
        return JsonResult.success ().data("cid",id);
    }

    //根据课程ID查询课程基本信息
    @GetMapping("getCourseInfo/{courseId}")
    public JsonResult getCourseInfo(@PathVariable String courseId){
        CourseInfoVo courseInfoVo = eduCourseService.getCourseInfo(courseId);
        return JsonResult.success ().data ( "courseInfoVo",courseInfoVo );
    }

    //修改课程信息
    @PostMapping("/eidtCourseInfo/dec")
    public JsonResult updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        eduCourseService.updateCourseInfo ( courseInfoVo );
        return JsonResult.success ();
    }
    //根据课程ID查询课程确认信息
    @GetMapping("getPublishCourseInfo/{id}")
    public JsonResult getPublishInfo(@PathVariable String id){
        CoursePublishVo publishVo = eduCourseService.publishCourseInfo(id);
        return JsonResult.success ().data ( "publishInfo",publishVo);
    }

    //课程发布(修改课程状态)
    @PostMapping("/publishCourse/{id}")
    public JsonResult publishCourse(@PathVariable String id){
        EduCourse eduCourse = new EduCourse ();
        eduCourse.setId ( id );
        eduCourse.setStatus ( "Normal" );
        eduCourseService.updateById ( eduCourse );
        return JsonResult.success ();
    }

    //删除课程
    @DeleteMapping("delete/{courseId}")
    public JsonResult deleteCourse(@PathVariable String courseId){
        eduCourseService.removeCourse(courseId);
        return JsonResult.success ();

    }

}

