package com.hong.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hong.commonUtils.JsonResult;
import com.hong.eduservice.entity.EduTeacher;
import com.hong.eduservice.entity.vo.TeacherQuery;
import com.hong.eduservice.service.EduTeacherService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.net.Inet4Address;
import java.util.List;

/**
 *
 *
 * @author testjava
 * @since 2020-06-11
 */


@RestController
@RequestMapping("/edu")
@CrossOrigin
public class EduTeacherController {
    @Autowired
    private EduTeacherService teacherService;

    @GetMapping(value = "/find/teacher")
    public JsonResult findAll(){
        List<EduTeacher> teachers = teacherService.list ( null );
        return JsonResult.success ().data ( "list",teachers );
    }

    @ApiOperation ( value = "讲师删除")
    @DeleteMapping("/delete/{id}")
    public JsonResult remove(@PathVariable String id) {
        boolean flag = teacherService.removeById ( id );
        if (flag) {
            System.out.println ("delete.........");
            return JsonResult.success ();
        } else {
            return JsonResult.error ();
        }
    }

    /**
     * 讲师分页查询
     * @param current 当前页
     * @param limit   每页数量
     * @return
     */
    @GetMapping("/page/{current}/{limit}")
    public JsonResult pageTeacher(@PathVariable Integer current,@PathVariable Integer limit){
        Page<EduTeacher> page = new Page<EduTeacher> (1,3);
        //调用方法实现分页 把所有数据封装到page对象里面
        teacherService.page ( page,null );
        long total = page.getTotal ();
        List<EduTeacher> rows = page.getRecords ();
        return JsonResult.success ().data ( "total",total ).data ( "rows",rows);
    }

    /**
     * 多条件组合查询
     * @param current 当前页
     * @param limit   size
     * @param teacherQuery 条件对象
     * @return
     */
    @PostMapping("/condition/{current}/{limit}")
    public JsonResult pageCondition(@PathVariable Integer current, @PathVariable Integer limit,@RequestBody(required = false) TeacherQuery teacherQuery){
        //创建page对象
       Page<EduTeacher> pageTeacher = new Page<> ( current, limit);
       //创建Wragger
       QueryWrapper<EduTeacher> query = new QueryWrapper<> (  );
       //多条件组合查询：类似mybatis的动态sql
       //判断条件是否为空,不为空拼接条件
        String name = teacherQuery.getName ();
        Integer level = teacherQuery.getLevel ();
        String begin = teacherQuery.getBegin ();
        String end = teacherQuery.getEnd ();
        if(!StringUtils.isEmpty ( name )){
            query.like ( "name",name );
        }
        if(!StringUtils.isEmpty ( level )){
            query.eq ( "level",level );
        }
        if(!StringUtils.isEmpty ( begin )){
            query.ge( "gmt_create",begin );
        }
        if(!StringUtils.isEmpty ( end )){
            query.le( "gmt_modified",end );
        }
        //排序
        query.orderByDesc ( "gmt_create" );
        //调用方法实现条件分页查询
       teacherService.page ( pageTeacher,query );
        long total = pageTeacher.getTotal ();
        List<EduTeacher> rows = pageTeacher.getRecords ();
        return JsonResult.success ().data ( "total",total ).data ( "rows",rows );
    }

    /**
     * 讲师添加接口
     * @param teacher
     * @return
     */
    @PostMapping("/add")
    public JsonResult addTeacher(@RequestBody EduTeacher teacher){
        boolean flag = teacherService.save ( teacher );
        if(flag){
            return JsonResult.success ();
        }else {
            return JsonResult.error ();
        }
    }

    /**
     * 根据讲师ID查询
     * @param id
     * @return
     */
    @GetMapping("/get/teacher/{id}")
    public JsonResult getTeacher(@PathVariable String id){

        EduTeacher teacher = teacherService.getById ( id );
        System.out.println (teacher);
        return JsonResult.success ().data ( "teacher",teacher );
    }

    /**
     * 修改讲师接口
     * @param teacher
     * @return
     */
    @PostMapping("update/teacher")
    public JsonResult updateTeacher(@RequestBody EduTeacher teacher){
        boolean flag = teacherService.updateById ( teacher );
        System.out.println (".........."+flag);
        if(flag){
            return JsonResult.success ();
        }else {
            return JsonResult.error ();
        }
    }


    /**
     * 登录
     * @return
     */
    @PostMapping("/login")
    public JsonResult login(){
        System.out.println ("login.............");
        return JsonResult.success ().data ( "token","admin" );
    }

    @GetMapping("/info")
    public JsonResult getInfo(){
        return JsonResult.success ().data ( "roles","admin" ).data ( "name","admin" ).data ( "avatar","QAQ-_-" );
    }
}

