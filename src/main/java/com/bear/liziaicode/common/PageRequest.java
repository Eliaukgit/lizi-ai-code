package com.bear.liziaicode.common;

import lombok.Data;
//对于"分页"、“删除某条数据”这类通用的请求，可以封装统一的请求包装类，用于接收前端传来的参数，
// 之后相同参数的请求就不用再新建个类了。
//分页请求包装类，包括当前页号、页面大小、排序字段、排序顺序参数：
@Data
public class PageRequest {

    /**
     * 当前页号
     */
    private int pageNum = 1;

    /**
     * 页面大小
     */
    private int pageSize = 10;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序顺序（默认降序）
     */
    private String sortOrder = "descend";
}
