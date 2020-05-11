package ru.xmlboxes.demo.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.xmlboxes.demo.dao.model.ItemModel;
import ru.xmlboxes.demo.dao.repository.ItemRepository;
import ru.xmlboxes.demo.service.converter.ItemModelConverter;
import ru.xmlboxes.demo.service.dto.ItemServiceModel;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

@Service
public class ItemService {

    private ItemRepository itemRepository;
    private ItemModelConverter itemModelConverter;

    @Autowired
    public ItemService(ItemRepository itemRepository, ItemModelConverter itemModelConverter) {
        this.itemRepository = itemRepository;
        this.itemModelConverter = itemModelConverter;
    }

    public Optional<ItemServiceModel> findById(Long id) {
        ItemModel itemModel = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found!"));
        ItemServiceModel itemServiceModel = itemModelConverter.convertToServiceModel(itemModel);

        return Optional.ofNullable(itemServiceModel);
    }

    public Collection<ItemServiceModel> findAll() {
        Collection<ItemModel> itemModels = itemRepository.findAll();
        Collection<ItemServiceModel> itemServiceModels = new HashSet<>();

        for (ItemModel model : itemModels) {
            itemServiceModels.add(itemModelConverter.convertToServiceModel(model));
        }

        return itemServiceModels;
    }

    public ItemServiceModel insert(ItemServiceModel boxServiceModel) {
        ItemModel insertedItemModel = itemRepository.save(itemModelConverter.convertToDaoModel(boxServiceModel));
        return itemModelConverter.convertToServiceModel(insertedItemModel);
    }
}
