package com.m2891.controller;

import com.m2891.pojo.R;
import com.m2891.pojo.dto.UserDto;
import com.m2891.service.UserService;
import com.m2891.util.DtoUtil;
import com.m2891.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController
{
    private UserService userService;
    
    @GetMapping("{nickName}")
    @PreAuthorize("hasAuthority('jjj')")
    public R<UserDto> findUserByNickName(@PathVariable("nickName") String nickName)
    {
        return R.success(DtoUtil.toUserDto(userService.findUserByNickName(nickName)));
    }
    
    @GetMapping
    public Object currentUser()
    {
        return R.success(DtoUtil.toUserDto(SecurityUtils.currentUser()));
    }

    /**
     * 根据积分排序查询用户
     */
    @GetMapping("getUserByScore")
    public Object getUserByScore()
    {
        return R.success(DtoUtil.toUserDto(userService.byScore()));
    }
}
