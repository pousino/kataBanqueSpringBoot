package com.example.katabanquespringboot.entity;

import com.example.katabanquespringboot.enums.TypeOperation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class OperationTest {
    
    @Autowired
    private TestEntityManager entityManager;

    private Operation operation;
    private CompteBancaire compteBancaire;
    private Client client;
    
    @BeforeEach
    void setUp() {
        client = new Client();
        client.setNom("Dupont");
        
        compteBancaire = new CompteBancaire();
        compteBancaire.setSolde(0d);
        
        operation = new Operation(TypeOperation.DEPOT, 100d);
    }
    
    @Test
    public void saveOperation() {
        
        Client savedClient = this.entityManager.persistAndFlush(client);
        compteBancaire.setClient(savedClient);
        CompteBancaire savedCompteBancaire = this.entityManager.persistAndFlush(compteBancaire);
        assertEquals(savedCompteBancaire.getSolde(), 0d);
        
        operation.setCompteBancaire(savedCompteBancaire);
        Operation savedOperation = this.entityManager.persistAndFlush(operation);
        assertEquals(savedOperation.getMontant(), 100d);
        assertEquals(savedOperation.getType(), TypeOperation.DEPOT);
    
    }

    
    
}