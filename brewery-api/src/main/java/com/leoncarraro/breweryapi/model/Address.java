package com.leoncarraro.breweryapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("JpaDataSourceORMInspection")
@Embeddable
@Getter
@Setter
public class Address {

    private String cep;
    private String number;
    private String complement;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

}
