package com.m2891.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.m2891.pojo.entity.SysConfig;
import com.m2891.pojo.entity.TopicNode;
import com.m2891.util.HibernateSession;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysServiceImpl implements SysService
{
    private static Map<String, Object> sysConfigMap = new HashMap<>();
    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public Map<String, Object> getSysConfig()
    {
//        if (!sysConfigMap.isEmpty())
//        {
//            return sysConfigMap;
//        }
        List<SysConfig> sysConfigs = HibernateSession.transaction(session -> {
            return session.createQuery("from SysConfig sc", SysConfig.class).getResultList();
        });
        Map<String, Object> map = new HashMap<>();
        for (SysConfig sysConfig : sysConfigs)
        {
            String value = sysConfig.getValue();
            if (value == null || value.isBlank())
            {
                map.put(sysConfig.getKey(), value);
            }else
            {
                Object valueOb = value;
                valueOb = value.startsWith("[") ? objectMapper.readValue(value, ArrayList.class) : valueOb;
                valueOb = value.startsWith("{") ? objectMapper.readValue(value, Map.class) : valueOb;
                map.put(sysConfig.getKey(), valueOb);
            }
            
        }
        sysConfigMap = map;
        return map;
    }

    @Override
    public List<TopicNode> getTopiNodes(String tag)
    {
        List<TopicNode> topicNodes = HibernateSession.transaction(session -> {
            return session.createQuery("from TopicNode", TopicNode.class).setReadOnly(true).getResultList();
        });
        if (tag != null)
        {
            TopicNode topicNode = new TopicNode();
            topicNode.setId(-1);
            topicNode.setName("推荐");
            topicNodes.add(0, topicNode);
            topicNode = new TopicNode();
            topicNode.setId(0);
            topicNode.setName("最新");
            topicNodes.add(0, topicNode);
            topicNode = new TopicNode();
            topicNode.setId(-2);
            topicNode.setName("关注");
            topicNodes.add(0, topicNode);
        }
        return topicNodes;
    }
}
