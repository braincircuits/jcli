package com.m2891.pojo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(
                name = "uniqueUser",
                columnNames = {
                        "entityId", "entityType","userId"
                }
        )
)
@Comment("点赞记录表")
public class LikeRecord extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("点赞记录id")
    private Integer id;
    @Comment("点赞用户id")
    private Integer userId;
    @Comment("点赞对象id")
    private Integer entityId;
    @Comment("点赞对象类型")
    private String entityType;
    @Comment("点赞状态 true：已点赞")
    @ColumnDefault("true")
    private boolean status;
}
