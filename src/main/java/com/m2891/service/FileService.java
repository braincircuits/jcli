package com.m2891.service;

import com.m2891.pojo.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;

public interface FileService
{

    /**
     * 文件上传
     * @param file 文件
     * @return 文件id
     */
    String upload(MultipartFile file);

    FileInfo byUuid(String uuid);
}
