package com.leoncarraro.breweryapi.controller;

import com.leoncarraro.breweryapi.model.enums.Origin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/origins")
public class OriginController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<String>> getAll() {
        return ResponseEntity.ok(Origin.getAll());
    }

}
