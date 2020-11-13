package com.leoncarraro.breweryapi.controller;

import com.leoncarraro.breweryapi.controller.uril.URIParamsConverter;
import com.leoncarraro.breweryapi.dto.BeerRequest;
import com.leoncarraro.breweryapi.dto.BeerResponse;
import com.leoncarraro.breweryapi.service.BeerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/beers")
@AllArgsConstructor
public class BeerController {

    private final BeerService beerService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<BeerResponse>> getAllWithFilterAndPagination(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "6") Integer size,
            String sku, String name, String stylesId, BigDecimal minValue, BigDecimal maxValue) {

        PageRequest pageRequest = PageRequest.of(page, size);
        List<Long> stylesList = URIParamsConverter.getList(stylesId);

        return ResponseEntity.ok(beerService.getAllWithFilterAndPagination(
                pageRequest, sku, name, stylesList, minValue, maxValue));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{sku}")
    public ResponseEntity<BeerResponse> getOneBySku(@PathVariable String sku) {
        return ResponseEntity.ok(beerService.getOneBySku(sku));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<BeerResponse> create(@RequestBody @Valid BeerRequest beerRequest) {
        BeerResponse beerResponse = beerService.create(beerRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{sku}")
                .buildAndExpand(beerResponse.getSku())
                .toUri();

        return ResponseEntity.created(location).body(beerResponse);
    }

    @RequestMapping(value = "/image", method = RequestMethod.POST)
    public ResponseEntity<Void> uploadBeerImage(@RequestParam MultipartFile file) {
        URI uri = beerService.uploadBeerImage(file);
        return ResponseEntity.created(uri).build();
    }

}
