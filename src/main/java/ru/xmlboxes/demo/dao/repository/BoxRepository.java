package ru.xmlboxes.demo.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.xmlboxes.demo.dao.model.BoxModel;

import java.util.List;

@Repository
public interface BoxRepository extends JpaRepository<BoxModel, Long> {
    List<BoxModel> findByContainedIn_BoxId(Long id);
}
