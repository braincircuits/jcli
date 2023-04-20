package com.m2891.pojo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table
@Comment("路由节点")
public class TopicNode extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Comment("节点名字")
    @Column(length = 5)
    private String name;
    
    @Comment("节点描述")
    @Column(length = 20)
    private String description;
    
    @Comment("节点排序")
    @ColumnDefault("0")
    private Integer orderNum;
    
    @Comment("节点logo")
    private String logo;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TopicNode topicNode = (TopicNode) o;
        return id != null && Objects.equals(id, topicNode.id);
    }

    @Override
    public int hashCode()
    {
        return getClass().hashCode();
    }
}
