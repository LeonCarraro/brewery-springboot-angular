package com.leoncarraro.breweryapi.mapper;

import com.leoncarraro.breweryapi.model.enums.Origin;

public class OriginMapper {

    public String asString(Origin origin) {
        return origin.getDescription();
    }

}
