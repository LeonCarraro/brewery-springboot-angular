package com.leoncarraro.breweryapi.repository;

import com.leoncarraro.breweryapi.model.Beer;

import java.math.BigDecimal;
import java.util.List;

public interface BeerRepositoryCustom {

    List<Beer> getAllWithFilter(String sku, String name, List<Long> style, BigDecimal minValue,
                                       BigDecimal maxValue);

}
