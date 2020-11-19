package com.hong.eduservice.service.impl;

import com.hong.eduservice.entity.EduCourse;
import com.hong.eduservice.entity.EduCourseDescription;
import com.hong.eduservice.entity.EduVideo;
import com.hong.eduservice.entity.vo.CourseInfoVo;
import com.hong.eduservice.entity.vo.CoursePublishVo;
import com.hong.eduservice.mapper.EduCourseMapper;
import com.hong.eduservice.service.EduChapterService;
import com.hong.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hong.eduservice.service.EduVideoService;
import com.hong.exception.UserDefinedException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {


    @Autowired
    private EduCourseDescriptionServiceImpl eduCourseDescriptionService;
    @Autowired
    private EduVideoService eduVideoService;
    @Autowired
    private EduChapterService eduChapterService;
    /**
     * 添加课程信息
     * @param courseInfoVo
     */
    @Override
    public String saveCourse(CourseInfoVo courseInfoVo) {
        //1.向课程表添加课程基本信息
        EduCourse eduCourse = new EduCourse ();
        BeanUtils.copyProperties (courseInfoVo,eduCourse);
        int i = baseMapper.insert ( eduCourse );
        if(i == 0){
            throw new UserDefinedException ( 200001,"添加失败..." );
        }
        //获取添加之后的课程ID
        String cid = eduCourse.getId ();

        //2.向课程简介表添加课程信息
        EduCourseDescription eduCourseDescription = new EduCourseDescription ();
        eduCourseDescription.setDescription ( courseInfoVo.getDescription () );
        //把课程描述id设置成课程id
        eduCourseDescription.setId ( cid );
        eduCourseDescriptionService.save ( eduCourseDescription );
        return cid;

    }

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        //查询课程表
        CourseInfoVo courseInfoVo = new CourseInfoVo ();
        EduCourse eduCourse = baseMapper.selectById ( courseId );
        BeanUtils.copyProperties ( eduCourse, courseInfoVo);
        //查询课程信息描述表
        EduCourseDescription courseDescription = eduCourseDescriptionService.getById ( courseId );
        courseInfoVo.setDescription ( courseDescription.getDescription () );
        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        //修改课程表
        EduCourse eduCourse = new EduCourse ();
        BeanUtils.copyProperties ( courseInfoVo, eduCourse);
        int i = baseMapper.updateById ( eduCourse );
        if(i == 0){
            throw new UserDefinedException ( 200001,"修改课程信息失败..." );
        }
        //修改描述表
        EduCourseDescription courseDescription = new EduCourseDescription ();
        //courseDescription.setId ( courseInfoVo.getId () );
        courseDescription.setDescription ( courseInfoVo.getDescription () );
        eduCourseDescriptionService.updateById ( courseDescription );
    }

    /**
     * 根据课程id查询，确认信息
     * @param id
     * @return
     */
    @Override
    public CoursePublishVo publishCourseInfo(String id) {
        CoursePublishVo publishCourseInfo = baseMapper.getPublishCourseInfo ( id );
        return publishCourseInfo;
    }

    /**
     * 根据课程Id删除课程
     * @param courseId
     */
    @Override
    public void removeCourse(String courseId) {
        //1根据id删除小节
        eduVideoService.removeByCourseId ( courseId );
        //2.根据id删除章节
        eduChapterService.removeByCourseId(courseId);
        //3.删除描述
        eduCourseDescriptionService.removeById ( courseId );
        //4.最后删除课程
        int result = baseMapper.deleteById ( courseId );
        if(result == 1){
            throw  new UserDefinedException ( 20001,"删除失败" );
        }

    }
}
