package com.hong.eduservice.service;

import com.hong.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

public interface EduVideoService extends IService<EduVideo> {

    //根据课程id删除小节
    void removeByCourseId(String courseId);
}
