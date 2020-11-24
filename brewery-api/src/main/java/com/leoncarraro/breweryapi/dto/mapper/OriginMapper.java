package com.leoncarraro.breweryapi.dto.mapper;

import com.leoncarraro.breweryapi.model.enums.Origin;

public class OriginMapper {

    public String asString(Origin origin) {
        return origin.getDescription();
    }

}
