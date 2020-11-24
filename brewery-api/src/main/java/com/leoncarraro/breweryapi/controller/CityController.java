package com.leoncarraro.breweryapi.controller;

import com.leoncarraro.breweryapi.dto.CityRequest;
import com.leoncarraro.breweryapi.dto.CityResponse;
import com.leoncarraro.breweryapi.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/cities")
@AllArgsConstructor
public class CityController {

    private final CityService cityService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CityResponse>> getAllByState(Long state) {
        return ResponseEntity.ok(cityService.getAllByState(state));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<CityResponse> getOneById(@PathVariable Long id) {
        return ResponseEntity.ok(cityService.getOneById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CityResponse> create (@RequestBody @Valid CityRequest cityRequest) {
        CityResponse cityResponse = cityService.create(cityRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cityResponse.getId())
                .toUri();

        return ResponseEntity.created(location).body(cityResponse);
    }

}
