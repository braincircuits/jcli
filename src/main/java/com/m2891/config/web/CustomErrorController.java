package com.m2891.config.web;

import com.m2891.util.Convert;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.util.WebUtils.ERROR_STATUS_CODE_ATTRIBUTE;
import static org.springframework.web.util.WebUtils.FORWARD_REQUEST_URI_ATTRIBUTE;

@RestController
public class CustomErrorController implements ErrorController
{
    @RequestMapping(value = "/error")
    public String handleError(HttpServletRequest request)
    {
        Integer code = Convert.toInt(request.getAttribute(ERROR_STATUS_CODE_ATTRIBUTE));
        if (code.equals(HttpStatus.NOT_FOUND.value()))
        {
            System.out.println(request.getAttribute(FORWARD_REQUEST_URI_ATTRIBUTE));
            return "404";
        }
        return "error";
    }

}
