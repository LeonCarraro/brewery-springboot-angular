package com.leoncarraro.breweryapi.controller;

import com.leoncarraro.breweryapi.dto.StateRequest;
import com.leoncarraro.breweryapi.dto.StateResponse;
import com.leoncarraro.breweryapi.service.StateService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/states")
@AllArgsConstructor
public class StateController {

    private final StateService stateService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<StateResponse>> getAll() {
        return ResponseEntity.ok(stateService.getAll());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<StateResponse> getOneById(@PathVariable Long id) {
        return ResponseEntity.ok(stateService.getOneById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<StateResponse> create(@RequestBody @Valid StateRequest stateRequest) {
        StateResponse stateResponse = stateService.create(stateRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(stateResponse.getId())
                .toUri();

        return ResponseEntity.created(location).body(stateResponse);
    }

}
