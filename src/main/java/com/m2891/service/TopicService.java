package com.m2891.service;

import com.m2891.pojo.R;
import com.m2891.pojo.entity.Topic;
import com.m2891.pojo.entity.TopicNode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface TopicService
{
    /**
     * 创建topic
     * @return topic创建后的id
     */
    R<Object> createTopic(Topic topic);

    /**
     * 分页查询topic
     */
    R<Object> topicList();

    R<Object> getTopicById(Integer topicId);

    void createTopicNode(TopicNode topicNode);
    
    List<TopicNode> findAllTopicNode();

    Set<Integer> topicNodeIdList();
}
