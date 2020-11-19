package com.hong.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hong.eduservice.entity.EduSubject;
import com.hong.eduservice.entity.excel.SubjectData;
import com.hong.eduservice.entity.subject.FirstSubject;
import com.hong.eduservice.entity.subject.SecondSubject;
import com.hong.eduservice.listener.SubjectExcelListener;
import com.hong.eduservice.mapper.EduSubjectMapper;
import com.hong.eduservice.service.EduSubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void parse(MultipartFile file,EduSubjectService subjectService) {
        try {
            InputStream inputStream = file.getInputStream ();
            EasyExcel.read ( inputStream, SubjectData.class,new SubjectExcelListener ( subjectService ) ).sheet ().doRead ();

        }catch (Exception e){
            e.printStackTrace ();
        }
    }

    @Override
    public List<FirstSubject> getFirstAndSecondSubject() {
        //1.查询一级分类
        QueryWrapper<EduSubject> wapperOne = new QueryWrapper (  );
        wapperOne.eq ( "parent_id",0 );
        List<EduSubject> oneLevel = baseMapper.selectList ( wapperOne );
        System.out.println ("1111"+oneLevel);
        //2.查询二级分类
        QueryWrapper<EduSubject> wapperTwo = new QueryWrapper (  );
        wapperTwo.ne ( "parent_id",0 );
        List<EduSubject> twoLevel = baseMapper.selectList ( wapperTwo );
        System.out.println ("2222"+twoLevel);

        //创建一个集合存储最终返回的数据
        List<FirstSubject> fianalData = new ArrayList<> (  );
        //3.封装一级分类数据
        for (int i = 0; i <oneLevel.size () ; i++) {//遍历oneLevel集合
            //获取对象
            EduSubject eduSubject = oneLevel.get ( i );
            //
            FirstSubject firstSubject = new FirstSubject ();
//            firstSubject.setId ( eduSubject.getId () );
//            firstSubject.setTitle (  eduSubject.getTitle ());
            BeanUtils.copyProperties ( eduSubject, firstSubject);
//            fianalData.add ( firstSubject );
            //4.封装二级分类数据
            ///创建集合封装二级集合
            List<SecondSubject> li = new ArrayList<> (  );
            for (int j = 0; j < twoLevel.size(); j++) {
                EduSubject secSubject = twoLevel.get ( j );
                if(secSubject.getParentId ().equals ( firstSubject.getId () )){
                    SecondSubject sec = new SecondSubject ();
                    BeanUtils.copyProperties ( secSubject,sec );
                    li.add ( sec );
                }
            }
            //把二级分类放在一级分类线面
            firstSubject.setChildren ( li );
            fianalData.add ( firstSubject );
        }
        return fianalData;
    }
}
