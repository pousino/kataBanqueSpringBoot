package com.example.katabanquespringboot.service.impl;

import com.example.katabanquespringboot.dto.OperationDto;
import com.example.katabanquespringboot.entity.CompteBancaire;
import com.example.katabanquespringboot.entity.Operation;
import com.example.katabanquespringboot.enums.TypeOperation;
import com.example.katabanquespringboot.repository.CompteBancaireRepository;
import com.example.katabanquespringboot.repository.OperationRepository;
import com.example.katabanquespringboot.service.IOperationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperationServiceImpl implements IOperationService {
    
    @Autowired
    OperationRepository operationRepository;
    
    @Autowired
    CompteBancaireRepository compteBancaireRepository;
    
    @Autowired
    @Qualifier("modelMapper")
    private ModelMapper modelMapper;
    
    @Override
    public OperationDto depot(Double montant) {
        Operation operation = new Operation(TypeOperation.DEPOT, montant);
        return getOperationDto(montant, operation);
    }
    
    private OperationDto getOperationDto(Double montant, Operation operation) {
        modelMapper = new ModelMapper();
        CompteBancaire compteBancaire  = compteBancaireRepository.findById(1);
        operation.setCompteBancaire(compteBancaire);
        
        compteBancaire.setSolde(compteBancaire.getSolde() + montant);
        compteBancaireRepository.save(compteBancaire);
        return modelMapper.map(operationRepository.save(operation), OperationDto.class);
    }
    
    
    @Override
    public OperationDto retrait(Double montant) {
        montant = montant > 0 ? (-1 * montant) : montant;
    
        Operation operation = new Operation(TypeOperation.RETRAIT, montant);
        return getOperationDto(montant, operation);
    }
    
    @Override
    public List<OperationDto> toutesOperationsOperationDtos() {
        modelMapper = new ModelMapper();
        List<Operation> operations = operationRepository.findAll();
        return operations
                .stream()
                .map(operation -> modelMapper.map(operation, OperationDto.class))
                .collect(Collectors.toList());
    }
    
    @Override
    public Double getSolde() {
        CompteBancaire compte = compteBancaireRepository.findById(1);
        return compte.getSolde();
    }
    
    public void setOperationRepository(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }
    
    public void setCompteBancaireRepository(CompteBancaireRepository compteBancaireRepository) {
        this.compteBancaireRepository = compteBancaireRepository;
    }
}
