package com.example.katabanquespringboot.web.controller;


import com.example.katabanquespringboot.dto.OperationDto;
import com.example.katabanquespringboot.dto.OperationRequestBody;
import com.example.katabanquespringboot.service.IOperationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.katabanquespringboot.config.SwaggerConfig;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Api(tags = { SwaggerConfig.COMPTE_BANCAIRE })
@RestController
public class OperationController {
    @Autowired
    private IOperationService operationService;
    
    @ApiOperation(value = "Afficher un historique des operations bancaires !")
    @GetMapping(value="/operations")
    public List<OperationDto> getToutesOperations() {
        return operationService.toutesOperationsOperationDtos();
    }
    
    @ApiOperation(value = "Afficher le solde bancaire !")
    @GetMapping(value="/solde")
    public Double getSolde() {
        return operationService.getSolde();
    }
    
    @ApiOperation(value = "Faire un depot sur votre compte !")
    @PostMapping(value = "/depot")
    public ResponseEntity<OperationDto> depot(@RequestBody OperationRequestBody operation) {
        OperationDto newOperation = operationService.depot(operation.getMontant());
        if(newOperation == null)
            return ResponseEntity.noContent().build();
        
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newOperation.getId())
                .toUri();
        return ResponseEntity.created(location).body(newOperation);
    }
    
    @ApiOperation(value = "Faire un retrait depuis votre compte !")
    @PostMapping(value = "/retrait")
    public ResponseEntity<OperationDto> retrait(@RequestBody OperationRequestBody operation) {
        OperationDto newOperation = operationService.retrait(operation.getMontant());
        
        if(newOperation == null)
            return ResponseEntity.noContent().build();
        
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newOperation.getId())
                .toUri();
        return ResponseEntity.created(location).body(newOperation);
    }
    
}
