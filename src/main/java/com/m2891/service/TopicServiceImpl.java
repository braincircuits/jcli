package com.m2891.service;

import com.m2891.constant.RCode;
import com.m2891.pojo.R;
import com.m2891.pojo.entity.Topic;
import com.m2891.pojo.entity.TopicNode;
import com.m2891.util.HibernateSession;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TopicServiceImpl implements TopicService
{
    @Override
    public R<Object> createTopic(Topic topic)
    {
        Set<Integer> topicNodeIdList = topicNodeIdList();
        if (!topicNodeIdList.contains(topic.getNodeId()))
        {
            return R.error(RCode.A10002);
        }
        topic.createInit();
        HibernateSession.transaction(session -> {
            session.persist(topic);
        });
        return R.success(topic.getId());
    }

    @Override
    public R<Object> topicList()
    {
        
        return null;
    }

    @Override
    public R<Object> getTopicById(Integer topicId)
    {
        TopicNode transaction = HibernateSession.transaction(session -> {
            return session.find(TopicNode.class, topicId);
        });
        return R.success(transaction);
    }

    @Override
    public void createTopicNode(TopicNode topicNode)
    {
        topicNode.createInit();
        HibernateSession.transaction(session -> {
            session.persist(session);
        });
    }
    @Override
    public List<TopicNode> findAllTopicNode()
    {
        return HibernateSession.transaction(session -> {
            return session.createQuery("from TopicNode", TopicNode.class).setReadOnly(true).getResultList();
        });
    }

    @Override
    public Set<Integer> topicNodeIdList()
    {
        List<TopicNode> topicNodeList = findAllTopicNode();
        return topicNodeList.stream().map(TopicNode::getId).collect(Collectors.toSet());
    }
}
