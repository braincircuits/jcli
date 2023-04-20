package com.m2891.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DController
{
    @GetMapping("/abc")
    public Object get(HttpServletRequest request)
    {
        HttpSession session1 = request.getSession();
        return session1.getId();
    }
}
