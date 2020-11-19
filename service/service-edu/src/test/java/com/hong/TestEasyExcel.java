package com.hong;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class TestEasyExcel {
    //文件名
    public static void main(String[] args) {
        String fileName = "F:/easy.xlsx";
        //easyExcel实现写操作
       // EasyExcel.write ( fileName,Entity.class ).sheet ("学生表").doWrite ( list() );

        //easyExcel实现读操作
        EasyExcel.read ( fileName,Entity.class,new ExcelListener () ).sheet ().doRead ();

    }

    private static  List<Entity> list(){
        List<Entity> lists = new ArrayList<> (  );

        for (int i = 0; i <=10 ; i++) {
            Entity en = new Entity ();
            en.setSno ( i );
            en.setName ( "阳"+i );
            lists.add ( en );
        }
        return lists;
    }
}
