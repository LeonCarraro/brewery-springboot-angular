package com.leoncarraro.breweryapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.leoncarraro.breweryapi.model.Beer;
import com.leoncarraro.breweryapi.model.enums.Flavor;
import com.leoncarraro.breweryapi.model.enums.Origin;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class BeerResponse {

    private final Long id;
    private final String sku;
    private final String name;
    private final String description;
    private final Integer volume;
    private final BigDecimal value;
    private final BigDecimal alcoholContent;
    private final BigDecimal comission;
    private final Integer stockQuantity;
    private final Origin origin;
    private final Flavor flavor;

    @JsonProperty(value = "style")
    private final StyleResponse styleResponse;

    public BeerResponse(Beer beer) {
        id = beer.getId();
        sku = beer.getSku();
        name = beer.getName();
        description = beer.getDescription();
        volume = beer.getVolume();
        value = beer.getValue();
        alcoholContent = beer.getAlcoholContent();
        comission = beer.getComission();
        stockQuantity = beer.getStockQuantity();
        origin = beer.getOrigin();
        flavor = beer.getFlavor();
        styleResponse = new StyleResponse(beer.getStyle());
    }

}
