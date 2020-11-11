package com.leoncarraro.breweryapi.repository;

import com.leoncarraro.breweryapi.model.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Long>, BeerRepositoryCustom {

    @Query(value = "SELECT beer FROM Beer beer JOIN FETCH beer.style WHERE beer.sku = :sku")
    Optional<Beer> findBySku(String sku);

    boolean existsBySku(String sku);

}
