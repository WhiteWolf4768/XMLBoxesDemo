package ru.xmlboxes.demo.service.converter;

import org.springframework.stereotype.Component;
import ru.xmlboxes.demo.dao.model.BoxModel;
import ru.xmlboxes.demo.service.dto.BoxServiceModel;

@Component
public class BoxModelConverter {
    public BoxServiceModel convertToServiceModel(BoxModel boxModel) {
        if (boxModel != null) {
            Long boxId = boxModel.getBoxId();
            BoxServiceModel containedIn;

            if (boxModel.getContainedIn() != null) {
                containedIn = convertToServiceModel(boxModel.getContainedIn());
            } else {
                containedIn = null;
            }

            BoxServiceModel boxServiceModel = new BoxServiceModel(boxId, containedIn);

            return boxServiceModel;
        } else {
            return null;
        }
    }

    public BoxModel convertToDaoModel(BoxServiceModel boxServiceModel) {
        if (boxServiceModel != null) {
            Long boxId = boxServiceModel.getBoxId();
            BoxModel containedIn;

            if (boxServiceModel.getContainedIn() != null) {
                containedIn = convertToDaoModel(boxServiceModel.getContainedIn());
            } else {
                containedIn = null;
            }

            BoxModel boxModel = new BoxModel(boxId, containedIn);

            return boxModel;
        } else {
            return  null;
        }
    }
}
