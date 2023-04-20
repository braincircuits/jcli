package com.m2891.util;

import com.m2891.pojo.dto.SysConfigDTO;
import com.m2891.pojo.dto.UserDto;
import com.m2891.pojo.entity.SysConfig;
import com.m2891.pojo.entity.SysUser;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class DtoUtil
{
    public static UserDto toUserDto(SysUser sysUser)
    {
        if (sysUser == null) return null;
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(sysUser, userDto);
        return userDto;
    }

    public static List<UserDto> toUserDto(List<SysUser> sysUserList)
    {
        if (sysUserList == null) return null;
        ArrayList<UserDto> userDtoList = new ArrayList<>(sysUserList.size());
        for (SysUser sysUser : sysUserList)
        {
            userDtoList.add(toUserDto(sysUser));
        }
        return userDtoList;
    }

    public static SysConfigDTO getSysConfigDto(SysConfig sysConfig)
    {
        SysConfigDTO sysConfigDTO = new SysConfigDTO();
        BeanUtils.copyProperties(sysConfigDTO, sysConfigDTO);
        return sysConfigDTO;
    }
}
