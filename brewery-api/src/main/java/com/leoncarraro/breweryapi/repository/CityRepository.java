package com.leoncarraro.breweryapi.repository;

import com.leoncarraro.breweryapi.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    @Query(value = "SELECT city FROM City city JOIN FETCH city.state WHERE city.state.id = :stateId")
    List<City> getAllByState(Long stateId);

    boolean existsByName(String name);

}
