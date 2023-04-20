package com.m2891.controller;

import com.m2891.pojo.R;
import com.m2891.pojo.entity.SysUser;
import com.m2891.pojo.entity.Topic;
import com.m2891.service.TopicService;
import com.m2891.util.IPUtils;
import com.m2891.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("topic")
public class TopicController
{
    @Autowired
    private TopicService topicService;
    @PostMapping("create")
    public Object createTopic(Topic topic)
    {
        SysUser sysUser = SecurityUtils.currentUser();
        topic.setUserId(sysUser.getUserId());
        topic.setIp(IPUtils.getCurrentRequestIp());
        return topicService.createTopic(topic);
    }
    @GetMapping("{id}")
    public Object getTopicById(@PathVariable("id") Integer id)
    {
        return topicService.getTopicById(id);
    }
    @GetMapping("topicList")
    public Object topicList()
    {
        return R.success();
    }
}
