package com.leoncarraro.breweryapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BeerRequest {

    private String sku;
    private String name;
    private String description;
    private Integer volume;
    private BigDecimal value;
    private BigDecimal alcoholContent;
    private BigDecimal comission;
    private Integer stockQuantity;
    private String origin;
    private String flavor;
    private Long styleId;

}
