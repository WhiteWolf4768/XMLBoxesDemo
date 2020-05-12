package ru.xmlboxes.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.xmlboxes.demo.service.db.DatabaseXmlInitializer;
import ru.xmlboxes.demo.service.task.ColorFinder;
import ru.xmlboxes.demo.web.dto.InputModel;

import java.io.IOException;
import java.util.Collection;

@RestController
public class ColorController {

    private DatabaseXmlInitializer databaseXmlInitializer;
    private ColorFinder colorFinder;

    @Autowired
    public ColorController(DatabaseXmlInitializer databaseXmlInitializer, ColorFinder colorFinder) {
        this.databaseXmlInitializer = databaseXmlInitializer;
        this.colorFinder = colorFinder;
    }

    @RequestMapping(value = "/findColor", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Collection<Long> jsonTest(@RequestBody InputModel inputModel) {
        return colorFinder.findColorInBox(inputModel.getBoxId(), inputModel.getColor());
    }
}
