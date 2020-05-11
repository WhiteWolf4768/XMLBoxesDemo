package ru.xmlboxes.demo.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class IndexController {

    @RequestMapping(value = {"/", "/index"})
    public ModelAndView index() {
        ModelAndView index = new ModelAndView();
        index.setViewName("index.html");
        return index;
    }

}
