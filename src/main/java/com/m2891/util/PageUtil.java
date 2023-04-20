package com.m2891.util;

import com.m2891.pojo.dto.PageDto;

public class PageUtil
{
    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";
    public static PageDto getPage()
    {
        PageDto pageDto = new PageDto();
        pageDto.setPageNum(ServletUtils.getParameterToInt(PAGE_NUM, 1));
        pageDto.setPageSize(ServletUtils.getParameterToInt(PAGE_SIZE, 10));
        return pageDto;
    }
}
