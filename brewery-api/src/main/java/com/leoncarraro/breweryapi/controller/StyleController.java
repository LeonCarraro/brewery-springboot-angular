package com.leoncarraro.breweryapi.controller;

import com.leoncarraro.breweryapi.dto.StyleRequest;
import com.leoncarraro.breweryapi.dto.StyleResponse;
import com.leoncarraro.breweryapi.service.StyleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/styles")
@AllArgsConstructor
public class StyleController {

    private final StyleService styleService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<StyleResponse>> getAll() {
        return ResponseEntity.ok(styleService.getAll());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<StyleResponse> getOneById(@PathVariable Long id) {
        return ResponseEntity.ok(styleService.getOneById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<StyleResponse> create(@RequestBody StyleRequest styleRequest) {
        StyleResponse styleResponse = styleService.create(styleRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(styleResponse.getId())
                .toUri();

        return ResponseEntity.created(location).body(styleResponse);
    }

}
