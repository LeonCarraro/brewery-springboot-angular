package com.leoncarraro.breweryapi.repository;

import com.leoncarraro.breweryapi.model.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StyleRepository extends JpaRepository<Style, Long> {

    boolean existsByName(String name);

}
