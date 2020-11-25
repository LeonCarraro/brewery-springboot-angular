package com.leoncarraro.breweryapi.dto.mapper;

import com.leoncarraro.breweryapi.model.enums.ClientType;

public class ClientTypeMapper {

    public String asString(ClientType clientType) {
        return clientType.getDescription();
    }

}
