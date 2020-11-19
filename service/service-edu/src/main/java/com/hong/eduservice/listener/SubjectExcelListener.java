package com.hong.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hong.eduservice.entity.EduSubject;
import com.hong.eduservice.entity.excel.SubjectData;
import com.hong.eduservice.service.EduSubjectService;
import com.hong.exception.UserDefinedException;

import java.util.Objects;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
    public EduSubjectService subjectService;
    public SubjectExcelListener(){}
    public SubjectExcelListener(EduSubjectService subjectService){
        this.subjectService=subjectService;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if(subjectData == null){
            throw new UserDefinedException (20001,"文件为空....");
        }

        //判断一级分类是否重复
        EduSubject existOne = existOneSubject ( subjectService, subjectData.getOneSubjectName () );
        if(Objects.isNull(existOne)){
            //没有同一级分类进行添加
            existOne = new EduSubject ();
            existOne.setParentId ( "0" );
            existOne.setTitle ( subjectData.getOneSubjectName () );
            subjectService.save ( existOne );
        }

        //判断二级分类是否重复
        //获取pid
        String pid = existOne.getId ();
        EduSubject existTwo = existTowSubject ( subjectService, subjectData.getTwoSubjectName (), pid );
        if(Objects.isNull(existTwo)){
            existTwo = new EduSubject ();
            existTwo.setTitle ( subjectData.getTwoSubjectName () );
            existTwo.setParentId ( pid );
            subjectService.save ( existTwo );
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    /**
     * 一级分类不能重复
     * @param subjectService
     * @param name
     * @return
     */
    private EduSubject existOneSubject(EduSubjectService subjectService,String name){
        QueryWrapper<EduSubject> wapper = new QueryWrapper<> (  );
        wapper.eq ( "title",name );
        wapper.eq("parent_id",0);
        EduSubject oneSubject =subjectService.getOne ( wapper );
        return oneSubject;
    }

    /**
     * 判断二级分类不能重复
     * @param subjectService
     * @param name
     * @param pid
     * @return
     */
    private EduSubject existTowSubject(EduSubjectService subjectService,String name,String pid){
        QueryWrapper<EduSubject> wapper = new QueryWrapper<> (  );
        wapper.eq ( "title",name );
        wapper.eq("parent_id",pid);
        EduSubject towSubject =subjectService.getOne ( wapper );
        return towSubject;
    }

}
