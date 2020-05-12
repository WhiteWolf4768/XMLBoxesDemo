package ru.xmlboxes.demo.service.parser;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemXml {
    @JacksonXmlProperty(isAttribute = true)
    private Long id;

    @JacksonXmlProperty(isAttribute = true)
    private String color;
}
