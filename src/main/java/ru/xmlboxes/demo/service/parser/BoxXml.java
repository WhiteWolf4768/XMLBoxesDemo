package ru.xmlboxes.demo.service.parser;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoxXml {
    @JacksonXmlProperty(isAttribute = true)
    private Long id;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Item")
    private List<ItemXml> itemList;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Box")
    private List<BoxXml> boxList;

    public void setBoxList(List<BoxXml> value){
        if (boxList == null){
            boxList = new ArrayList<>(value.size());
        }
        boxList.addAll(value);
    }

    public void setItemList(List<ItemXml> value){
        if (itemList == null){
            itemList = new ArrayList<>(value.size());
        }
        itemList.addAll(value);
    }
}
