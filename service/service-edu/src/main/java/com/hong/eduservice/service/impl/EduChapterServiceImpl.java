package com.hong.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hong.eduservice.entity.EduChapter;
import com.hong.eduservice.entity.EduVideo;
import com.hong.eduservice.entity.chapter.ChapterVO;
import com.hong.eduservice.entity.chapter.VideoVo;
import com.hong.eduservice.mapper.EduChapterMapper;
import com.hong.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hong.eduservice.service.EduVideoService;
import com.hong.exception.UserDefinedException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 2020-06-30
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    private EduVideoService eduVideoService; //注入小节service
    @Override
    public List<ChapterVO> getChapterVideoByCourseId(String courseId) {
        //1.根据课程id查询章节
        QueryWrapper<EduChapter> wapperChapter = new QueryWrapper<> (  );
        wapperChapter.eq ( "course_id",courseId );
        List<EduChapter> eduChapterList = baseMapper.selectList ( wapperChapter );

        //2.查询小节
        QueryWrapper<EduVideo> wapperVideo = new QueryWrapper<> (  );
        wapperChapter.eq ( "course_id",courseId );
        List<EduVideo> videoList = eduVideoService.list ( wapperVideo );

        //3.编列章节list集合，进行封装(先创建一个集合封装数据)
        List<ChapterVO> chapterVOList = new ArrayList<> (  );
        for (int i = 0; i < eduChapterList.size (); i++) {
            //得到每个章节
            EduChapter eduChapter = eduChapterList.get ( i );
            //把eduChapter赋值给chapterVOList
            ChapterVO chapterVO = new ChapterVO ();
            BeanUtils.copyProperties ( eduChapter,chapterVO );
            chapterVOList.add ( chapterVO );
            //创建结婚进行封装小节数据
            List<VideoVo> eduVideoVo = new ArrayList<> (  );

            //4.编列小节list集合，进行封装
            for (int j = 0; j < videoList.size (); j++) {
                EduVideo eduVideo = videoList.get ( j );
                //判断小节里面的章节id是否一样
                if(eduVideo.getChapterId ().equals ( eduChapter.getId () )){
                    VideoVo videoVo = new VideoVo ();
                    BeanUtils.copyProperties ( eduVideo,videoVo );
                    eduVideoVo.add ( videoVo );
                }
            }
            //把小节集合放到章节中
            chapterVO.setChildren ( eduVideoVo );
        }

        return chapterVOList;
    }

    //删除章节
    @Override
    public boolean deleteChapter(String chapterId) {
        //根据chapterid查询小节表有数据不进行删除，没有才删除
        QueryWrapper<EduVideo> videoWapper = new QueryWrapper<> (  );
        videoWapper.eq("chapter_id",chapterId);
        int count = eduVideoService.count ( videoWapper );
        if(count>0){
            //有数据不删
            throw new UserDefinedException ( 20001,"有数据不能删除" );
        }else {
            //删除
            int result = baseMapper.deleteById ( chapterId );
            return result>0;
        }
    }

    /**
     * 删除章节
     * @param courseId
     */
    @Override
    public void removeByCourseId(String courseId) {
        QueryWrapper wrapper = new QueryWrapper (  );
        wrapper.eq ( "course_id",courseId );
        baseMapper.delete ( wrapper );
    }
}
