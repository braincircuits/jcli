package com.m2891.pojo.dto;

import lombok.Data;

import java.util.Collection;

@Data
public class PageDto
{
    /**当前页**/
    private Integer pageNum;
    /**每页显示记录数**/
    private Integer pageSize;
    private Integer current;
    private Collection<Object> data;
    public void setData(Collection<Object> data)
    {
        this.data=data;
    }

    public Integer getStartIndex()
    {
        return (pageNum-1)*pageSize;
    }
}
