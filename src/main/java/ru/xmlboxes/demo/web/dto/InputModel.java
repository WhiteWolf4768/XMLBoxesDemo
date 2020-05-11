package ru.xmlboxes.demo.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputModel {
    private Long boxId;
    private String color;
}
