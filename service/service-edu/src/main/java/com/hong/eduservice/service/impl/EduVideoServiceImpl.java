package com.hong.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hong.eduservice.entity.EduVideo;
import com.hong.eduservice.mapper.EduVideoMapper;
import com.hong.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 璇剧▼瑙嗛? 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-06-30
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {
    /**
     * 删除小节
     * TODO 删除小节 删除对应的视频
     * @param courseId
     */
    @Override
    public void removeByCourseId(String courseId) {
        QueryWrapper wrapper = new QueryWrapper (  );
        wrapper.eq ( "course_id", courseId);
        baseMapper.delete ( wrapper );

    }
}
