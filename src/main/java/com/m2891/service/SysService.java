package com.m2891.service;

import com.m2891.pojo.entity.TopicNode;

import java.util.List;
import java.util.Map;

public interface SysService
{
    Map<String, Object> getSysConfig();

    List<TopicNode> getTopiNodes(String tag);
}
