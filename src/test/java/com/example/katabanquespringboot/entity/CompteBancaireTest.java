package com.example.katabanquespringboot.entity;

import com.example.katabanquespringboot.repository.ClientRepository;
import com.example.katabanquespringboot.repository.CompteBancaireRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.BeforeEach;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CompteBancaireTest {
    
    @Autowired
    private TestEntityManager entityManager;
    
    private static Client client;
    private static CompteBancaire compteBancaire;
    
    @BeforeEach
    void setUp() {
        client = new Client();
        client.setNom("Dupont");
        
        compteBancaire = new CompteBancaire();
        compteBancaire.setSolde(10240d);
    }
    
    @Test
    public void saveClientAndSaveCompte() {
        Client savedClient = this.entityManager.persistAndFlush(client);
        compteBancaire.setClient(savedClient);
        CompteBancaire savedCompte = this.entityManager.persistAndFlush(compteBancaire);
        assertEquals(savedCompte.getSolde(), 10240d);
    }
    

    @Test
    public void createClientNullException() throws Exception {
        CompteBancaire compteInvalide = new CompteBancaire();
        compteInvalide.setSolde(10240d);
        compteInvalide.setClient(null);
        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            this.entityManager.persistAndFlush(compteInvalide);
        });
        
        String expectedMessage = "Client est obligatoire";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

}