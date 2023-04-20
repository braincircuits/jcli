package com.m2891.pojo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table
public class Topic extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Comment("主题所在的节点")
    private Integer nodeId;
    @Comment("发表主题的用户")
    private Integer userId;
    
    @Column(length = 20)
    @Comment("标题")
    private String title;
    
    @Column(columnDefinition = "text")
    @Comment("内容")
    private String content;

    @Column(columnDefinition = "text")
    @Comment("隐藏部分")
    private String hideContent;
    
    @Comment("浏览量")
    @ColumnDefault("0")
    private Integer viewCount=0;
    
    @Comment("评论总数")
    @ColumnDefault("0")
    private Integer commentCount=0;
    
    @Comment("点赞总数")
    @ColumnDefault("0")
    private Integer likeCount=0;
    
    @Comment("最后一次评论时间")
    private Date lastCommentTime;
    
    @Comment("最后一次评论用户id")
    private Integer lastCommentUserId;
    
    @Comment("主题发表时的IP")
    private String ip;
    
    @Column(columnDefinition = "text")
    private String extraData;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Topic topic = (Topic) o;
        return id != null && Objects.equals(id, topic.id);
    }

    @Override
    public int hashCode()
    {
        return getClass().hashCode();
    }
}
