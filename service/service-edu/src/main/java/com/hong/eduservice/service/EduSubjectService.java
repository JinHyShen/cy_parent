package com.hong.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hong.eduservice.entity.EduSubject;
import com.hong.eduservice.entity.subject.FirstSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EduSubjectService extends IService<EduSubject> {
    /**
     * 获取上传文件，进行解析
     * @param file
     * @param subjectService
     */
    void parse(MultipartFile file,EduSubjectService subjectService);

    List<FirstSubject> getFirstAndSecondSubject();
}

