package com.m2891.controller;

import com.m2891.constant.FileConstant;
import com.m2891.pojo.R;
import com.m2891.pojo.entity.FileInfo;
import com.m2891.service.FileService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RestController
@RequestMapping("file")
public class FileController
{
    @Autowired
    private FileService fileService;
    @PostMapping("upload")
    public Object upload(@RequestParam("file") MultipartFile upload)
    {
        return R.success(fileService.upload(upload));
    }
    
    @GetMapping("{uuid}")
    public Object download(@PathVariable("uuid") String uuid, HttpServletResponse response) throws FileNotFoundException
    {
        FileInfo fileInfo = fileService.byUuid(uuid);
        uuid = fileInfo.getUuid();
        File file = new File(FileConstant.path + uuid);
        if (uuid == null && !file.exists())
        {
            return R.error();
        }
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + file.getName());
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }

}
