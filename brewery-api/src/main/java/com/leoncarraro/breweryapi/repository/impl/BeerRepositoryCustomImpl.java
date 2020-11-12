package com.leoncarraro.breweryapi.repository.impl;

import com.leoncarraro.breweryapi.model.Beer;
import com.leoncarraro.breweryapi.repository.BeerRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
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

    public Page<Beer> getAllWithFilterAndPagination(Pageable pageable, String sku, String name, List<Long> styles, BigDecimal minValue,
                                       BigDecimal maxValue) {

        StringBuilder query = new StringBuilder();
        HashMap<String, Object> queryParameters = new HashMap<>();

        query.append("SELECT beer FROM Beer beer ");
        query.append("JOIN FETCH beer.style ");
        query.append("WHERE 0 = 0 ");

        if (StringUtils.hasText(sku)) {
            query.append("AND beer.sku LIKE :sku ");
            queryParameters.put("sku", "%" + sku + "%");
        }

        if (StringUtils.hasText(name)) {
            query.append("AND beer.name LIKE :name ");
            queryParameters.put("name", "%" + name + "%");
        }

        if (styles != null) {
            query.append("AND beer.style.id IN :styles ");
            queryParameters.put("styles", styles);
        }

        if (minValue != null) {
            query.append("AND beer.value >= :minValue ");
            queryParameters.put("minValue", minValue);
        }

        if (maxValue != null) {
            query.append("AND beer.value <= :maxValue ");
            queryParameters.put("maxValue", maxValue);
        }

        query.append("ORDER BY beer.sku DESC");

        TypedQuery<Beer> typedQuery = entityManager.createQuery(query.toString(), Beer.class);
        queryParameters.forEach(typedQuery::setParameter);

        int totalElements = typedQuery.getResultList().size();

        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());

        List<Beer> result = typedQuery.getResultList();

        return PageableExecutionUtils.getPage(result, pageable, () -> totalElements);
    }

}
