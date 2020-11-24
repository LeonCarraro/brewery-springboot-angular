package com.leoncarraro.breweryapi.dto.mapper;

import com.leoncarraro.breweryapi.model.enums.Flavor;

public class FlavorMapper {

    public String asString(Flavor flavor) {
        return flavor.getDescription();
    }

}
