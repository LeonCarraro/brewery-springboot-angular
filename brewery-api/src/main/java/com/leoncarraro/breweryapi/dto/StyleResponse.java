package com.leoncarraro.breweryapi.dto;

import com.leoncarraro.breweryapi.model.Style;
import lombok.Getter;

@Getter
public class StyleResponse {

    private final Long id;
    private final String name;

    public StyleResponse(Style style) {
        id = style.getId();
        name = style.getName();
    }

}
