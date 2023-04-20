package com.m2891.pojo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Comment;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table
@Comment("系统配置")
public class SysConfig extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "`key`")
    private String key;
    private String value;
    private String description;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SysConfig sysConfig = (SysConfig) o;
        return id != null && Objects.equals(id, sysConfig.id);
    }

    @Override
    public int hashCode()
    {
        return getClass().hashCode();
    }
}
