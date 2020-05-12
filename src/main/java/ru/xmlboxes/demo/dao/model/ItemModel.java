package ru.xmlboxes.demo.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "itemTbl")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemModel {
    @Id
    private Long itemId;

    @Column(name = "color")
    private String color;

    @ManyToOne
    @JoinColumn(name = "containedIn")
    private BoxModel containedIn;
}
