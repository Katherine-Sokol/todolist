package com.goit.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@RequestMapping("/test")
@Controller
public class TestController {
    @GetMapping(value = "/test")
    public ModelAndView testApp() {
        return new ModelAndView("test/test");
    }
}
