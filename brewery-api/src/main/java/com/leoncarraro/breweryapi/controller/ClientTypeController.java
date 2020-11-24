package com.leoncarraro.breweryapi.controller;

import com.leoncarraro.breweryapi.model.enums.ClientType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/clients")
public class ClientTypeController {

    @RequestMapping(method = RequestMethod.GET, value = "/types")
    public ResponseEntity<List<String>> getAllClientTypes() {
        return ResponseEntity.ok(ClientType.getAll());
    }

}
