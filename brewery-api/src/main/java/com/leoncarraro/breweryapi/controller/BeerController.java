package com.leoncarraro.breweryapi.controller;

import com.leoncarraro.breweryapi.controller.uril.URIParamsConverter;
import com.leoncarraro.breweryapi.dto.BeerRequest;
import com.leoncarraro.breweryapi.dto.BeerResponse;
import com.leoncarraro.breweryapi.service.BeerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/beers")
@AllArgsConstructor
public class BeerController {

    private final BeerService beerService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<BeerResponse>> getAllWithFilter(String sku, String name, String stylesId,
                                                               BigDecimal minValue, BigDecimal maxValue) {

        List<Long> stylesList = URIParamsConverter.getList(stylesId);
        return ResponseEntity.ok(beerService.getAllWithFilter(sku, name, stylesList, minValue, maxValue));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{sku}")
    public ResponseEntity<BeerResponse> getOneBySku(@PathVariable String sku) {
        return ResponseEntity.ok(beerService.getOneBySku(sku));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<BeerResponse> create(@RequestBody BeerRequest beerRequest) {
        BeerResponse beerResponse = beerService.create(beerRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{sku}")
                .buildAndExpand(beerResponse.getSku())
                .toUri();

        return ResponseEntity.created(location).body(beerResponse);
    }

}
