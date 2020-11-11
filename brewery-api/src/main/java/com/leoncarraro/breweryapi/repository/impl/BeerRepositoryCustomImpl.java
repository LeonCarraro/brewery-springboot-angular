package com.leoncarraro.breweryapi.repository.impl;

import com.leoncarraro.breweryapi.model.Beer;
import com.leoncarraro.breweryapi.repository.BeerRepositoryCustom;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Repository
public class BeerRepositoryCustomImpl implements BeerRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Beer> getAllWithFilter(String sku, String name, List<Long> styles, BigDecimal minValue,
                                       BigDecimal maxValue) {

        StringBuilder jpql = new StringBuilder();
        HashMap<String, Object> queryParameters = new HashMap<>();

        jpql.append("SELECT beer FROM Beer beer ");
        jpql.append("JOIN FETCH beer.style ");
        jpql.append("WHERE 0 = 0 ");

        if (StringUtils.hasText(sku)) {
            jpql.append("AND beer.sku LIKE :sku ");
            queryParameters.put("sku", "%" + sku + "%");
        }

        if (StringUtils.hasText(name)) {
            jpql.append("AND beer.name LIKE :name ");
            queryParameters.put("name", "%" + name + "%");
        }

        if (styles != null) {
            jpql.append("AND beer.style.id IN :styles ");
            queryParameters.put("styles", styles);
        }

        if (minValue != null) {
            jpql.append("AND beer.value >= :minValue ");
            queryParameters.put("minValue", minValue);
        }

        if (maxValue != null) {
            jpql.append("AND beer.value <= :maxValue");
            queryParameters.put("maxValue", maxValue);
        }

        TypedQuery<Beer> query = entityManager.createQuery(jpql.toString(), Beer.class);
        queryParameters.forEach(query::setParameter);

        return query.getResultList();
    }

}
