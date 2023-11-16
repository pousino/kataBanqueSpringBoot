package com.example.katabanquespringboot.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ClientTest {
    
    
    @Autowired
    private TestEntityManager entityManager;
    
    private Client client;
    
    
    @BeforeEach
    public void setUp() {
        client = new Client();
        client.setNom("Dupont");
        client.setPrenom("Jean");
        
    }
    
    
    @Test
    public void saveClient() {
        Client savedClient = this.entityManager.persistAndFlush(client);
        assertEquals(savedClient.getNom(), "Dupont", "Le nom du client ne matche pas");
    }
    
    
    @Test
    public void createClientBlankNameException() throws Exception {
        Client clientInvalide = new Client();
        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            this.entityManager.persistAndFlush(clientInvalide);
        });
        
        String expectedMessage = "Nom client obligatoire";
        String actualMessage = exception.getMessage();
        
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
}