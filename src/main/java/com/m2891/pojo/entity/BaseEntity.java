package com.m2891.pojo.entity;

import com.m2891.util.SecurityUtils;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity implements Serializable {
    @Comment("数据创建人")
    private Integer createBy;
    @CreationTimestamp
    @Comment("数据创建时间")
    private Instant createTime;
    @Comment("数据更新人")
    private Integer updateBy;
    @CreationTimestamp
    @Comment("数据更新时间")
    private Instant updateTime;

    public void createInit() {
        SysUser sysUser = SecurityUtils.currentUser();
        this.createBy = sysUser.getUserId();
        this.updateBy = sysUser.getUserId();
    }

    public void updateInit() {
        SysUser sysUser = SecurityUtils.currentUser();
        this.updateBy = sysUser.getUserId();
        this.updateTime = Instant.now();
    }
}
