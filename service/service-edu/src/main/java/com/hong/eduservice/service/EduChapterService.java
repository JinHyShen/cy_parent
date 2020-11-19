package com.hong.eduservice.service;

import com.hong.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hong.eduservice.entity.chapter.ChapterVO;

import java.util.List;

public interface EduChapterService extends IService<EduChapter> {
    //课程大纲列表,根据课程id进行查询
    List<ChapterVO> getChapterVideoByCourseId(String courseId);

    boolean deleteChapter(String chapterId);

    void removeByCourseId(String courseId);
}
