package com.leoncarraro.breweryapi.model.enums;

import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
public enum Flavor {

    ADOCICADA("Adocicada"),
    AMARGA("Amarga"),
    FORTE("Forte"),
    FRUTADA("Frutada"),
    SUAVE("Suave");

    private final String description;

    Flavor(String description) {
        this.description = description;
    }

    public static List<String> getAll() {
        return Arrays.stream(Flavor.values()).map(Flavor::getDescription).collect(Collectors.toList());
    }

    public static Optional<Flavor> getByDescription(String description) {
        Flavor flavorFound = null;

        if (StringUtils.hasText(description)) {
            for (Flavor flavor : Flavor.values()) {
                if (flavor.getDescription().equals(description)) {
                    flavorFound = flavor;
                }
            }
        }

        return Optional.ofNullable(flavorFound);
    }

}
