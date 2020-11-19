package com.hong.eduservice.controller;

import com.hong.commonUtils.JsonResult;
import com.hong.eduservice.entity.subject.FirstSubject;
import com.hong.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/subject")
@CrossOrigin(origins = "*")
public class EduSubjectController {
    @Resource
    private EduSubjectService eduSubjectService;

    /**
     * 添加课程，读取上传数据
     * @param file
     * @return
     */
    @PostMapping("/addSubject")
    public JsonResult addSubject(MultipartFile file){
        //解析上传文件
        eduSubjectService.parse(file,eduSubjectService);
        return JsonResult.success ();
    }

    /**
     * 课程分了列表，树形结构
     * @return
     */
    @GetMapping("/getAllSubject")
    public JsonResult getSubjects(){
        List<FirstSubject> lists =eduSubjectService.getFirstAndSecondSubject();
        return JsonResult.success ().data ( "list",lists );
    }
}
