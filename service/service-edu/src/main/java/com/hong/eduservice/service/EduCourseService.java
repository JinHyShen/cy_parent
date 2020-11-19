package com.hong.eduservice.service;

import com.hong.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hong.eduservice.entity.vo.CourseInfoVo;
import com.hong.eduservice.entity.vo.CoursePublishVo;

public interface EduCourseService extends IService<EduCourse> {
    /**
     * 添加课程信息
     * @param courseInfoVo
     */
    String saveCourse(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo publishCourseInfo(String id);
    //删除课程
    void removeCourse(String courseId);
}
