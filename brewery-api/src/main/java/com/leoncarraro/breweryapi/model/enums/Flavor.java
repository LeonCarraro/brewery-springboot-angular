package com.leoncarraro.breweryapi.model.enums;

import lombok.Getter;

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

}
