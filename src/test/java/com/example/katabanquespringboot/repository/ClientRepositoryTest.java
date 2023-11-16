package com.example.katabanquespringboot.repository;

import com.example.katabanquespringboot.entity.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.UnixDomainSocketAddress;
import java.time.LocalDate;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

//@ExtendWith(SpringExtension provides a bridge between Spring Boot test features and JUnit.
@ExtendWith(SpringExtension.class)
//Integration Testing With @DataJpaTest
@DataJpaTest
class ClientRepositoryTest {
    
    @Autowired
    private ClientRepository clientRepository;
    
    private static Client client;
    
    @BeforeEach
    void setUp() {
        client = new Client();
        client.setNom("Tartampion");
        client.setPrenom("Jean");
        client.setAdresse("1 rue de la Paix");
        client.setTelephone("0123456789");
        LocalDate dateNaissance = LocalDate.of(1990, 1, 1);
        client.setDateNaissance(dateNaissance);
    }
    
    @Test
    public void should_find_1_client_if_repository_has_been_initialised() {
        Collection clients = clientRepository.findAll();
        assertTrue(clients.size() == 1);
    }
    
    @Test
    public void should_store_client_and_find_by_id(){
        Client savedClient = this.clientRepository.save(client);
        assertEquals(this.clientRepository.findById(savedClient.getId()).get(), client);
    }
    
    @Test
    public void should_store_client_and_find_by_name(){
        Client savedClient = this.clientRepository.save(client);
        assertEquals(this.clientRepository.findByNom(savedClient.getNom()).get(0), client);
    }
    
    @Test
    public void should_save_one_client_and_delete_all_clients() {
        Client savedClient = this.clientRepository.save(client);
        clientRepository.deleteAll();
        assertTrue(clientRepository.findAll().isEmpty());
    }
}