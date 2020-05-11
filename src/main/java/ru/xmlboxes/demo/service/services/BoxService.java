package ru.xmlboxes.demo.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.xmlboxes.demo.dao.model.BoxModel;
import ru.xmlboxes.demo.dao.model.ItemModel;
import ru.xmlboxes.demo.dao.repository.BoxRepository;
import ru.xmlboxes.demo.dao.repository.ItemRepository;
import ru.xmlboxes.demo.service.converter.BoxModelConverter;
import ru.xmlboxes.demo.service.converter.ItemModelConverter;
import ru.xmlboxes.demo.service.dto.BoxServiceModel;
import ru.xmlboxes.demo.service.dto.Storable;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class BoxService {

    private BoxRepository boxRepository;
    private ItemRepository itemRepository;
    private BoxModelConverter boxModelConverter;
    private ItemModelConverter itemModelConverter;

    @Autowired
    public BoxService(BoxRepository boxRepository, ItemRepository itemRepository, BoxModelConverter boxModelConverter, ItemModelConverter itemModelConverter) {
        this.boxRepository = boxRepository;
        this.itemRepository = itemRepository;
        this.boxModelConverter = boxModelConverter;
        this.itemModelConverter = itemModelConverter;
    }

    public Optional<BoxServiceModel> findById(Long id) {
        BoxModel boxModel;
        boxModel = boxRepository.findById(id).orElseThrow(() -> new RuntimeException("Box not found!"));
        BoxServiceModel boxServiceModel = boxModelConverter.convertToServiceModel(boxModel);

        return Optional.ofNullable(boxServiceModel);
    }

    public Collection<BoxServiceModel> findAll() {
        Collection<BoxModel> boxModels = boxRepository.findAll();
        Collection<BoxServiceModel> boxServiceModels = new HashSet<>();

        for (BoxModel model : boxModels) {
            boxServiceModels.add(boxModelConverter.convertToServiceModel(model));
        }

        return boxServiceModels;
    }

    public BoxServiceModel insert(BoxServiceModel boxServiceModel) {
        BoxModel insertedBoxModel = boxRepository.save(boxModelConverter.convertToDaoModel(boxServiceModel));
        return boxModelConverter.convertToServiceModel(insertedBoxModel);
    }

    public Collection<Storable> getStoredItems(BoxServiceModel boxServiceModel) {
        Collection<Storable> storables = new HashSet<>();

        Long boxId = boxServiceModel.getBoxId();
        List<BoxModel> boxModels = boxRepository.findByContainedIn_BoxId(boxId);
        List<ItemModel> itemModels = itemRepository.findByContainedIn_BoxId(boxId);

        for (BoxModel boxModel : boxModels) {
            storables.add(boxModelConverter.convertToServiceModel(boxModel));
        }

        for (ItemModel itemModel : itemModels) {
            storables.add(itemModelConverter.convertToServiceModel(itemModel));
        }

        return storables;
    }
}
