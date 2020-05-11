package ru.xmlboxes.demo.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "boxTbl")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoxModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boxId;

    @ManyToOne
    @JoinColumn(name = "containedIn")
    private BoxModel containedIn;
}
