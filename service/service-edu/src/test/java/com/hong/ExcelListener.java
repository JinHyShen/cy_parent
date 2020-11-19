package com.hong;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

public class ExcelListener extends AnalysisEventListener<Entity> {
    /**
     * 一行一行的读取excel内容
     * @param entity
     * @param analysisContext
     */
    @Override
    public void invoke(Entity entity, AnalysisContext analysisContext) {
        System.out.println ("---------"+entity);

    }

    /**
     * 读取表头
     * @param headMap
     * @param context
     */
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context){
        System.out.println ("表头:"+headMap);
    }
    /**
     * 读取之后的操作
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
