package com.leoncarraro.breweryapi.model.validation.group;

import com.leoncarraro.breweryapi.dto.ClientRequest;
import com.leoncarraro.breweryapi.model.enums.ClientType;
import com.leoncarraro.breweryapi.service.exceptions.BadRequestException;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class ClientGroupSequenceProvider implements DefaultGroupSequenceProvider<ClientRequest> {

    @Override
    public List<Class<?>> getValidationGroups(ClientRequest clientRequest) {
        List<Class<?>> validationGroups = new ArrayList<>();

        validationGroups.add(ClientRequest.class);
        if (clientRequest != null && clientRequest.getClientType() != null) {
            validationGroups.add(getClientType(clientRequest).getGroup());
        }

        return validationGroups;
    }

    private ClientType getClientType(ClientRequest clientRequest) {
        return ClientType.getByDescription(clientRequest.getClientType())
                .orElseThrow(() -> new BadRequestException(
                        "Tipo de cliente '" + clientRequest.getClientType() + "' não encontrado!"));
    }

}
