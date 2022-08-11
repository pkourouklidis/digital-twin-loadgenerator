/**
 * @author Joost Noppen (611749237), BetaLab, Applied Research
 * Date: 04/07/2022
 * Copyright (c) British Telecommunications plc 2022
 **/


package com.bt.betalab.callcentre.loadgenerator.controller;

import com.bt.betalab.callcentre.loadgenerator.api.LoadRequest;
import com.bt.betalab.callcentre.loadgenerator.service.LoadGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin
public class Controller {

    @Autowired
    LoadGeneratorService loadGeneratorService;

    @PostMapping(produces = "application/json", value = "api/v1/generate")
    public ResponseEntity generateLoad(@RequestBody LoadRequest jsonRequest)  {
        loadGeneratorService.setupLoad(jsonRequest);
        return ResponseEntity.ok().build();
    }
}
