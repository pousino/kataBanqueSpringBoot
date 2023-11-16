package com.example.katabanquespringboot.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.example.katabanquespringboot.entity.Client;
import com.example.katabanquespringboot.entity.CompteBancaire;

import static org.junit.jupiter.api.Assertions.*;

//@ExtendWith(SpringExtension provides a bridge between Spring Boot test features and JUnit.
@ExtendWith(SpringExtension.class)
//Integration Testing With @DataJpaTest
@DataJpaTest
class CompteBancaireRepositoryTest {
    
    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private CompteBancaireRepository compteBancaireRepository;
    
    private static Client client;
    private static CompteBancaire compteBancaire;
    
    
    @BeforeEach
    void setUp() {
        client = new Client();
        client.setNom("Dupont");
    
        compteBancaire = new CompteBancaire();
        compteBancaire.setSolde(990d);
    }
    
    @Test
    public void should_store_compte_and_find_by_id() {
        Client savedClient = this.clientRepository.save(client);
        compteBancaire.setClient(savedClient);
        CompteBancaire compteBancaireSaved = this.compteBancaireRepository.save(compteBancaire);
        assertTrue(compteBancaireSaved.getId() > 0);
        assertTrue(compteBancaireRepository.existsById(compteBancaireSaved.getId()));
    }
    
    
}