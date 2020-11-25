package com.leoncarraro.breweryapi.repository;

import com.leoncarraro.breweryapi.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(value = "SELECT client FROM Client client " +
            "JOIN FETCH client.address.city city JOIN FETCH city.state " +
            "WHERE client.name LIKE %:name% " +
            "AND client.document LIKE :document%",
            countQuery = "SELECT COUNT(client) FROM Client client " +
                    "WHERE client.name LIKE %:name% " +
                    "AND client.document LIKE :document%")
    Page<Client> getAllWithFilterAndPagination(Pageable pageable, String name, String document);

    boolean existsByDocument(String document);

}
