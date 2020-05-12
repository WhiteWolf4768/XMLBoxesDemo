package ru.xmlboxes.demo.service.serializer;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.xmlboxes.demo.service.dto.BoxServiceModel;
import ru.xmlboxes.demo.service.dto.ItemServiceModel;
import ru.xmlboxes.demo.service.services.BoxService;
import ru.xmlboxes.demo.service.services.ItemService;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class XmlSerializer {

    private BoxService boxService;
    private ItemService itemService;

    @Autowired
    public XmlSerializer(BoxService boxService, ItemService itemService) {
        this.boxService = boxService;
        this.itemService = itemService;
    }

    public void serializeXML() throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(ToXmlGenerator.Feature.WRITE_XML_1_1);
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        DatabaseSerializeModel db = new DatabaseSerializeModel();
        List<BoxSerializeModel> boxList = new ArrayList<>();
        List<ItemSerializeModel> itemList = new ArrayList<>();

        Collection<BoxServiceModel> boxServiceModels = boxService.findAll();
        Collection<ItemServiceModel> itemServiceModels = itemService.findAll();

        for (BoxServiceModel boxModel : boxServiceModels) {
            boxList.add(getBoxModel(boxModel));
        }

        for (ItemServiceModel itemModel : itemServiceModels) {
            itemList.add(getItemModel(itemModel));
        }

        db.setBoxList(boxList);
        db.setItemList(itemList);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        xmlMapper.writeValue(byteArrayOutputStream, db);

        try(OutputStream outputStream = new FileOutputStream("log.xml")) {
            byteArrayOutputStream.writeTo(outputStream);
        }
    }

    private BoxSerializeModel getBoxModel(BoxServiceModel boxServiceModel) {
        Long boxId = boxServiceModel.getBoxId();
        String containedIn;

        if (boxServiceModel.getContainedIn() == null) {
            containedIn = "null";
        } else {
            containedIn = String.valueOf(boxServiceModel.getContainedIn().getBoxId());
        }

        BoxSerializeModel boxSerializeModel = new BoxSerializeModel(boxId, containedIn);

        return boxSerializeModel;
    }

    private ItemSerializeModel getItemModel(ItemServiceModel itemServiceModel) {
        Long itemId = itemServiceModel.getItemId();
        String color;
        String containedIn;

        if (itemServiceModel.getColor() == null) {
            color = "null";
        } else {
            color = itemServiceModel.getColor();
        }

        if (itemServiceModel.getContainedIn() == null) {
            containedIn = "null";
        } else {
            containedIn = String.valueOf(itemServiceModel.getContainedIn().getBoxId());
        }

        ItemSerializeModel itemSerializeModel = new ItemSerializeModel(itemId, color, containedIn);

        return itemSerializeModel;
    }

}
