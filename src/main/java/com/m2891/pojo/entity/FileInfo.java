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
@Comment("文件上传信息")
public class FileInfo extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String uuid;
    
    @Column(unique = true)
    private Long hash;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FileInfo fileInfo = (FileInfo) o;
        return id != null && Objects.equals(id, fileInfo.id);
    }

    @Override
    public int hashCode()
    {
        return getClass().hashCode();
    }
}
