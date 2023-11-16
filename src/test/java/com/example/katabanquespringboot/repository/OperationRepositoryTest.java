package com.example.katabanquespringboot.repository;

import com.example.katabanquespringboot.enums.TypeOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.example.katabanquespringboot.entity.Client;
import com.example.katabanquespringboot.entity.CompteBancaire;
import com.example.katabanquespringboot.entity.Operation;

import static org.junit.jupiter.api.Assertions.*;
//@ExtendWith(SpringExtension provides a bridge between Spring Boot test features and JUnit.
// Whenever we are using any Spring Boot testing features in our JUnit tests, this annotation will be required.
//@RunWith(SpringRunner.class) is replaced by junit5 @ExtendWith(SpringExtension.class)
@ExtendWith(SpringExtension.class)
//Integration Testing With @DataJpaTest
@DataJpaTest
class OperationRepositoryTest {
/*
@DataJpaTest provides some standard setup needed for testing the persistence layer:
configuring H2, an in-memory database,setting Hibernate, Spring Data,
 and the DataSource, performing an @EntityScan . turning on SQL logging
 */
    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private CompteBancaireRepository compteBancaireRepository;
    
    @Autowired
    private OperationRepository operationRepository;
    
    private static Client client;
    private static CompteBancaire compteBancaire;
    private static Operation operation;
    
    @BeforeEach
    void setUp() {
        client = new Client();
        client.setNom("Dupont");
        compteBancaire = new CompteBancaire();
        compteBancaire.setSolde(990d);
        operation = new Operation(TypeOperation.DEPOT, 100d);
    }
    
    @Test
    public void should_store_operation_and_find_by_id() {
        Client savedClient = this.clientRepository.save(client);
        compteBancaire.setClient(savedClient);
        CompteBancaire compteBancaireSaved = this.compteBancaireRepository.save(compteBancaire);
        assertEquals(compteBancaireSaved.getSolde(), 990d);
    
        operation.setCompteBancaire(compteBancaireSaved);
        Operation operationSaved = this.operationRepository.save(operation);
        assertEquals(operationSaved.getType(), TypeOperation.DEPOT);
        assertEquals(operationSaved.getMontant(), 100d);
    }
    
    

}