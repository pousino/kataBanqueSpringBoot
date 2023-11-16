package com.example.katabanquespringboot.service;

import com.example.katabanquespringboot.dto.OperationDto;

import java.util.List;

public interface IOperationService {
    
    OperationDto depot(Double montant);
    
    OperationDto retrait(Double montant);
    
    List<OperationDto> toutesOperationsOperationDtos();
    
    Double getSolde();
    
}
