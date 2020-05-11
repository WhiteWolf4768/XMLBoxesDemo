package ru.xmlboxes.demo.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemServiceModel implements Storable {
    private Long itemId;
    private String color;
    private BoxServiceModel containedIn;
}