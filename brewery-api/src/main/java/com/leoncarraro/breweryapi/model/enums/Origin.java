package com.leoncarraro.breweryapi.model.enums;

import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
public enum Origin {

    NACIONAL("Nacional"),
    INTERNACIONAL("Internacional");

    private final String description;

    Origin(String description) {
        this.description = description;
    }

    public static List<String> getAll() {
        return Arrays.stream(Origin.values()).map(Origin::getDescription).collect(Collectors.toList());
    }

    public static Optional<Origin> getByDescription(String description) {
        Origin originFound = null;

        if (StringUtils.hasText(description)) {
            for (Origin origin : Origin.values()) {
                if (origin.getDescription().equals(description)) {
                    originFound = origin;
                }
            }
        }

        return Optional.ofNullable(originFound);
    }

}
