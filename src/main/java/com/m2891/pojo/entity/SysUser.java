package com.m2891.pojo.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Where(clause = "is_Del=0")
@Table(
        uniqueConstraints = @UniqueConstraint(
                name = "uniqueUser",
                columnNames = {
                        "email", "isDel"
                }
        )
)
@Comment("用户信息")
public class SysUser extends BaseEntity implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("用户id")
    private Integer userId;
    
    @Column(length = 10)
    @Comment("用户昵称")
    private String nickName;
    
    @Column(length = 50)
    @Comment("用户邮箱")
    private String email;

    @Comment("邮箱是否验证")
    @ColumnDefault("false")
    private boolean emailVerified;
    
    @ColumnDefault("2")
    @Comment("用户性别")
    private Integer gender;
    
    @Comment("用户头像地址")
    private String avatar;
    
    @Comment("背景图片")
    private String backgroundImage;
    
    @Comment("登录密码")
    private String password;
    
    @Comment("个人签名")
    @Column(columnDefinition = "text")
    private String description;
    
    @Comment("积分")
    private Integer score;
    
    @Comment("话题总数")
    private Integer topicCount;
    
    @Comment("粉丝总数")
    private Integer fansCount;
    
    @Comment("关注的人数")
    private Integer followCount;
    
    @ColumnDefault("true")
    @Comment("账户是否启用")
    private boolean status;
    
    @Comment("用户被禁时间")
    private Date forbiddenEndTime;
    
    @Comment("用户是否被删除(0: 未删除) 删除时赋值id")
    @ColumnDefault("0")
    private Integer isDel;
    
    @Column(length = 50)
    @Comment("用户最后登录IP")
    private String loginIp;
    
    @Comment("用户最后登录时间")
    private Date loginDate;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SysUser sysUser = (SysUser) o;
        return userId != null && Objects.equals(userId, sysUser.userId);
    }

    @Override
    public int hashCode()
    {
        return getClass().hashCode();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return Collections.singleton(new SimpleGrantedAuthority("abc"));
    }

    @Override
    public String getUsername()
    {
        return this.nickName;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return status;
    }
}
