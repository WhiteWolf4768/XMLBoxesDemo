package ru.xmlboxes.demo.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.xmlboxes.demo.dao.model.ItemModel;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemModel, Long> {
    List<ItemModel> findByContainedIn_BoxId(Long id);
}
