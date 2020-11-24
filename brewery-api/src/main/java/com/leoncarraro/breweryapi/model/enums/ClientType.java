package com.leoncarraro.breweryapi.model.enums;

import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
public enum ClientType {

    FISICA("Pessoa Física"),
    JURIDICA("Pessoa Jurídica");

    private final String description;

    ClientType(String description) {
        this.description = description;
    }

    public static List<String> getAll() {
        return Arrays.stream(ClientType.values()).map(ClientType::getDescription).collect(Collectors.toList());
    }

    public static Optional<ClientType> getByDescription(String description) {
        ClientType clientTypeFound = null;

        if (StringUtils.hasText(description)) {
            for (ClientType type : ClientType.values()) {
                if (type.getDescription().equalsIgnoreCase(description)) {
                    clientTypeFound = type;
                }
            }
        }

        return Optional.ofNullable(clientTypeFound);
    }

}
