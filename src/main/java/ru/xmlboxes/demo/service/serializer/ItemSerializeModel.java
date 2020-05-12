package ru.xmlboxes.demo.service.serializer;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemSerializeModel {
    @JacksonXmlProperty(localName = "itemId")
    private Long itemId;
    @JacksonXmlProperty(localName = "color")
    private String color;
    @JacksonXmlProperty(localName = "containedIn")
    private String containedIn;
}
