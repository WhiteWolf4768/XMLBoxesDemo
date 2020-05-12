package ru.xmlboxes.demo.service.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.xmlboxes.demo.service.dto.BoxServiceModel;
import ru.xmlboxes.demo.service.dto.ItemServiceModel;
import ru.xmlboxes.demo.service.parser.BoxXml;
import ru.xmlboxes.demo.service.parser.ItemXml;
import ru.xmlboxes.demo.service.parser.Storage;
import ru.xmlboxes.demo.service.parser.XMLParser;
import ru.xmlboxes.demo.service.services.BoxService;
import ru.xmlboxes.demo.service.services.ItemService;

import java.io.IOException;
import java.util.Collection;

@Component
public class DatabaseXmlInitializer {
    private XMLParser xmlParser;
    private BoxService boxService;
    private ItemService itemService;

    @Autowired
    public DatabaseXmlInitializer(XMLParser xmlParser, BoxService boxService, ItemService itemService) {
        this.xmlParser = xmlParser;
        this.boxService = boxService;
        this.itemService = itemService;
    }

    public void InitializeDatabase(String xmlPath) throws IOException {
        Storage storage = xmlParser.parseXML(xmlPath);
        System.out.println(storage.toString());

        insertItems(storage.getItemList(), null);
        insertBoxes(storage.getBoxList(), null);

    }

    private void insertBoxes(Collection<BoxXml> boxes, BoxServiceModel containedIn) {
        BoxServiceModel boxServiceModel = new BoxServiceModel();
        for (BoxXml box : boxes) {
            boxServiceModel.setBoxId(box.getId());
            boxServiceModel.setContainedIn(containedIn);

            System.out.println("BOX - " + boxServiceModel.toString());

            boxService.insert(boxServiceModel);
            System.out.println("Inserted box - " + boxServiceModel.toString());

            if (box.getItemList() != null) {
                insertItems(box.getItemList(), boxServiceModel);
            }

            if (box.getBoxList() != null) {
                insertBoxes(box.getBoxList(), boxServiceModel);
            }
        }
    }

    private void insertItems(Collection<ItemXml> items, BoxServiceModel containedIn) {
        ItemServiceModel itemServiceModel = new ItemServiceModel();
        for (ItemXml item : items) {
            itemServiceModel.setItemId(item.getId());
            itemServiceModel.setColor(item.getColor());
            itemServiceModel.setContainedIn(containedIn);

            System.out.println("ITEM - " + itemServiceModel.toString());

            itemService.insert(itemServiceModel);
            System.out.println("Inserted item - " + itemServiceModel.toString());
        }
    }
}
