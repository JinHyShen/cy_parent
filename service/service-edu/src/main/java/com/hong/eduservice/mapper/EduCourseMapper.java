package com.hong.eduservice.mapper;

import com.hong.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hong.eduservice.entity.vo.CoursePublishVo;

public interface EduCourseMapper extends BaseMapper<EduCourse> {
    public CoursePublishVo getPublishCourseInfo(String courseId);

}
