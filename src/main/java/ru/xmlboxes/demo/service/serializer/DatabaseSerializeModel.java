package ru.xmlboxes.demo.service.serializer;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "H2Database")
public class DatabaseSerializeModel {

    @JacksonXmlElementWrapper(localName = "Boxes")
    @JacksonXmlProperty(localName = "Box")
    private List<BoxSerializeModel> boxList;

    @JacksonXmlElementWrapper(localName = "Items")
    @JacksonXmlProperty(localName = "Item")
    private List<ItemSerializeModel> itemList;

}
