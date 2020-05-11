package ru.xmlboxes.demo.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoxServiceModel implements Storable {
    private Long boxId;
    private BoxServiceModel containedIn;
}