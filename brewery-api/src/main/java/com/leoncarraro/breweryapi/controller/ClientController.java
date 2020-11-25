package com.leoncarraro.breweryapi.controller;

import com.leoncarraro.breweryapi.dto.ClientRequest;
import com.leoncarraro.breweryapi.dto.ClientResponse;
import com.leoncarraro.breweryapi.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/api/clients")
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ClientResponse>> getAllWithFilterAndPagination(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "6") Integer size,
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "document", defaultValue = "") String document
    ) {

        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.DESC, "name");
        return ResponseEntity.ok(clientService.getAllWithFilterAndPagination(pageRequest, name, document));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<ClientResponse> getOneById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getOneById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ClientResponse> create(@RequestBody @Valid ClientRequest clientRequest) {
        ClientResponse clientResponse = clientService.create(clientRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(clientResponse.getId())
                .toUri();

        return ResponseEntity.created(location).body(clientResponse);
    }

}
