package com.leoncarraro.breweryapi.model;

import com.leoncarraro.breweryapi.dto.BeerRequest;
import com.leoncarraro.breweryapi.model.enums.Flavor;
import com.leoncarraro.breweryapi.model.enums.Origin;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_beer")
@Getter
@Setter
@EqualsAndHashCode
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sku;
    private String name;
    private String description;
    private Integer volume;
    private BigDecimal value;
    private BigDecimal alcoholContent;
    private BigDecimal comission;
    private Integer stockQuantity;

    @Enumerated(value = EnumType.STRING)
    private Origin origin;

    @Enumerated(value = EnumType.STRING)
    private Flavor flavor;

    @ManyToOne
    @JoinColumn(name = "style_id")
    private Style style;

    public Beer() {
    }

    public Beer(BeerRequest beerRequest, Origin origin, Flavor flavor, Style style) {
        id = null;
        sku = beerRequest.getSku();
        name = beerRequest.getName();
        description = beerRequest.getDescription();
        volume = beerRequest.getVolume();
        value = beerRequest.getValue();
        alcoholContent = beerRequest.getAlcoholContent();
        comission = beerRequest.getComission();
        stockQuantity = beerRequest.getStockQuantity();
        this.origin = origin;
        this.flavor = flavor;
        this.style = style;
    }

    @PrePersist @PreUpdate
    private void prePersistAndUpdate() {
        setSku(getSku().toUpperCase());
    }

}
