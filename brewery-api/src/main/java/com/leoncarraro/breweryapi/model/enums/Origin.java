package com.leoncarraro.breweryapi.model.enums;

import lombok.Getter;

@Getter
public enum Origin {

    NACIONAL("Nacional"),
    INTERNACIONAL("Internacional");

    private final String description;

    Origin(String description) {
        this.description = description;
    }

}
