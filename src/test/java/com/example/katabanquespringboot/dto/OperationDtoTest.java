package com.example.katabanquespringboot.dto;

import com.example.katabanquespringboot.entity.Client;
import com.example.katabanquespringboot.entity.CompteBancaire;
import com.example.katabanquespringboot.entity.Operation;
import com.example.katabanquespringboot.enums.TypeOperation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class OperationDtoTest {
    private ModelMapper modelMapper = new ModelMapper();
    
    private static Operation operation;
    private static OperationDto operationDto;
    
    
    //@BeforeAll is used to signal that the annotated method should be executed before all tests in the current test class
    //In contrast to @BeforeEach methods, @BeforeAll methods are only executed once for a given test class.
    @BeforeAll
    public static void setUp() {
    
        Client client = new Client();
        client.setNom("Dupont");
        client.setPrenom("Jean");
        
        CompteBancaire compteBancaire = new CompteBancaire();
        compteBancaire.setClient(client);
        compteBancaire.setSolde(1000d);
        
        
        operation = new Operation();
        operation.setCompteBancaire(compteBancaire);
        operation.setType(TypeOperation.DEPOT);
        operation.setMontant(100d);
    
        operationDto = new OperationDto();
        operationDto.setCompteBancaireClientNom("Dupont");
        operationDto.setCompteBancaireSolde(500d);
        operationDto.setType(TypeOperation.RETRAIT);
        operationDto.setMontant(-100d);
        operationDto.setDate(LocalDateTime.now());
    }
    
    
    @Test
    public void mapOperationEntityToOperationDto() {
        OperationDto dto = modelMapper.map(operation, OperationDto.class);
        assertEquals(operation.getType(), dto.getType(), "Test mapping Type de l'operation");
        assertEquals(operation.getMontant(), dto.getMontant(), "Test mapping Montant de l'operation");
        assertEquals(operation.getCompteBancaire().getSolde(), dto.getCompteBancaireSolde(),  "Test mapping Solde du compte bancaire");
        assertEquals(operation.getCompteBancaire().getClient().getNom(), dto.getCompteBancaireClientNom(), "Test mapping Nom du client");
    }
    
    @Test
    public void mapOperationDtoToOperationEntity() {
        Operation op = modelMapper.map(operationDto, Operation.class);
        assertEquals(op.getType(), operationDto.getType(), "Test mapping Type de l'operation");
        assertEquals(op.getMontant(), operationDto.getMontant(), "Test mapping Montant de l'operation");
        assertEquals(op.getCompteBancaire().getSolde(), operationDto.getCompteBancaireSolde(), "Test mapping Solde du compte bancaire" );
        assertEquals(op.getCompteBancaire().getClient().getNom(), operationDto.getCompteBancaireClientNom(), "Test mapping Nom du client");
    }
    
}