package ru.xmlboxes.demo.service.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.xmlboxes.demo.dao.model.BoxModel;
import ru.xmlboxes.demo.dao.model.ItemModel;
import ru.xmlboxes.demo.service.dto.BoxServiceModel;
import ru.xmlboxes.demo.service.dto.ItemServiceModel;

@Component
public class ItemModelConverter {

    private BoxModelConverter boxModelConverter;

    @Autowired
    public ItemModelConverter(BoxModelConverter boxModelConverter) {
        this.boxModelConverter = boxModelConverter;
    }

    public ItemServiceModel convertToServiceModel(ItemModel itemModel) {
        if (itemModel != null) {
            Long itemId = itemModel.getItemId();
            String color = itemModel.getColor();
            BoxServiceModel containedIn;

            if (itemModel.getContainedIn() != null) {
                containedIn = boxModelConverter.convertToServiceModel(itemModel.getContainedIn());
            } else {
                containedIn = null;
            }

            ItemServiceModel itemServiceModel = new ItemServiceModel(itemId, color, containedIn);

            return itemServiceModel;
        } else {
            return null;
        }
    }

    public ItemModel convertToDaoModel(ItemServiceModel itemServiceModel) {
        if (itemServiceModel != null) {
            Long itemId = itemServiceModel.getItemId();
            String color = itemServiceModel.getColor();
            BoxModel containedIn;

            if (itemServiceModel.getContainedIn() != null) {
                containedIn = boxModelConverter.convertToDaoModel(itemServiceModel.getContainedIn());
            } else {
                containedIn = null;
            }

            ItemModel itemModel= new ItemModel(itemId, color, containedIn);

            return itemModel;
        } else {
            return  null;
        }
    }
}

