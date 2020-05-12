package ru.xmlboxes.demo.service.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.xmlboxes.demo.service.dto.BoxServiceModel;
import ru.xmlboxes.demo.service.dto.ItemServiceModel;
import ru.xmlboxes.demo.service.dto.Storable;
import ru.xmlboxes.demo.service.services.BoxService;
import ru.xmlboxes.demo.service.services.ItemService;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class ColorFinder {

    private BoxService boxService;
    private ItemService itemService;

    @Autowired
    public ColorFinder(BoxService boxService, ItemService itemService) {
        this.boxService = boxService;
        this.itemService = itemService;
    }

    public Collection<Long> findColorInBox(Long boxId, String color) {
        Collection<Long> itemsId;
        Collection<Storable> storables;

        if (boxId != null) {
            BoxServiceModel boxServiceModel = boxService.findById(boxId).orElseThrow(() -> new RuntimeException("Box not found!"));
            storables = boxService.getStoredItems(boxServiceModel);

            System.out.println(storables);

            itemsId = findColor(storables, color);

        } else {
            storables = boxService.getStoredItems(null);
            System.out.println(storables);

            itemsId = findColor(storables, color);
        }

        System.out.println(itemsId.toString());

        return itemsId;
    }

    private Collection<Long> findColor(Collection<Storable> storables, String color) {
        Collection<Long> itemsId = new ArrayList<>();

        for (Storable str : storables) {
            if (str instanceof ItemServiceModel) {
                ItemServiceModel tmpItem = ((ItemServiceModel) str);
                if (color == null) {
                    if (tmpItem.getColor() == null) {
                        itemsId.add(tmpItem.getItemId());
                    }
                } else {
                    if (tmpItem.getColor() != null) {
                        if (tmpItem.getColor().equals(color)) {
                            itemsId.add(tmpItem.getItemId());
                        }
                    }
                }
            } else if (str instanceof BoxServiceModel) {
                BoxServiceModel tmpBox = ((BoxServiceModel) str);
                Long boxId = tmpBox.getBoxId();
                Collection<Storable> nextStorables = boxService.getStoredItems(
                        boxService.findById(boxId).orElseThrow(() -> new RuntimeException("Box not found!")));
                itemsId.addAll(findColor(nextStorables, color));
            }
        }

        return itemsId;
    }
}
