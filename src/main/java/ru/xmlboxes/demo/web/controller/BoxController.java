package ru.xmlboxes.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.xmlboxes.demo.service.dto.BoxServiceModel;
import ru.xmlboxes.demo.service.services.BoxService;
import ru.xmlboxes.demo.web.dto.InputModel;

import java.util.ArrayList;
import java.util.Collection;

@RestController
public class BoxController {

    private BoxService boxService;

    @Autowired
    public BoxController(BoxService boxService) {
        this.boxService = boxService;
    }

    @RequestMapping(value = "/box")
    public ModelAndView findAll() {
        Collection<BoxServiceModel> boxServiceModels = boxService.findAll();
        ModelAndView view = new ModelAndView();
        view.setViewName("box.html");
        view.addObject("boxModels", boxServiceModels);
        return view;
    }

    @RequestMapping(value = "/jsontest", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Collection<Long> jsonTest(@RequestBody InputModel inputModel) {
        Collection<Long> collection = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            collection.add(Long.valueOf(i));
        }
        return collection;
    }

}
