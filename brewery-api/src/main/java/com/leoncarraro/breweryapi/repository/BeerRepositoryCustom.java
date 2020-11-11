package com.leoncarraro.breweryapi.repository;

import com.leoncarraro.breweryapi.model.Beer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface BeerRepositoryCustom {

    Page<Beer> getAllWithFilterAndPagination(Pageable pageable, String sku, String name, List<Long> style, BigDecimal minValue,
                                BigDecimal maxValue);

}
