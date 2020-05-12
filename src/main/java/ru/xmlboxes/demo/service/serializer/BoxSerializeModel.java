package ru.xmlboxes.demo.service.serializer;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoxSerializeModel {
    @JacksonXmlProperty(localName = "boxId")
    private Long boxId;
    @JacksonXmlProperty(localName = "containedIn")
    private String containedIn;
}
