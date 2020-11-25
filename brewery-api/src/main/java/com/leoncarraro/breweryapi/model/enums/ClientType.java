package com.leoncarraro.breweryapi.model.enums;

import com.leoncarraro.breweryapi.model.validation.group.CNPJGroup;
import com.leoncarraro.breweryapi.model.validation.group.CPFGroup;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
public enum ClientType {

    FISICA("Pessoa Física", CPFGroup.class),
    JURIDICA("Pessoa Jurídica", CNPJGroup.class);

    private final String description;
    private final Class<?> group;

    ClientType(String description, Class<?> group) {
        this.description = description;
        this.group = group;
    }

    public static List<String> getAll() {
        return Arrays.stream(ClientType.values()).map(ClientType::getDescription).collect(Collectors.toList());
    }

    public static Optional<ClientType> getByDescription(String description) {
        ClientType clientTypeFound = null;

        if (StringUtils.hasText(description)) {
            for (ClientType type : ClientType.values()) {
                if (type.getDescription().equals(description)) {
                    clientTypeFound = type;
                }
            }
        }

        return Optional.ofNullable(clientTypeFound);
    }

}
