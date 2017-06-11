package com.taskmgmt.exceptions;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TaskExceptionHandler implements ErrorController {

    private static final Logger log = LoggerFactory.getLogger(TaskExceptionHandler.class);
    private static final String PATH = "/error";

    @Override
    public String getErrorPath() {
        return PATH;
    }

    @RequestMapping(PATH)
    public ModelAndView handleErr(HttpServletResponse res){
        ModelAndView mav = new ModelAndView("exc");
        int errCode = res.getStatus();
        log.info("Displaying error view; error code {}", errCode);
        mav.addObject("errCode", errCode);
        return mav;
    }
}
