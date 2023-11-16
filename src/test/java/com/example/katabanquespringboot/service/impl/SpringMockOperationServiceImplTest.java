package com.example.katabanquespringboot.service.impl;

import com.example.katabanquespringboot.dto.OperationDto;
import com.example.katabanquespringboot.entity.CompteBancaire;
import com.example.katabanquespringboot.entity.Operation;
import com.example.katabanquespringboot.repository.CompteBancaireRepository;
import com.example.katabanquespringboot.repository.OperationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

//@RunWith(SpringRunner.class) is replaced by junit5 @ExtendWith(SpringExtension.class)
@ExtendWith(SpringExtension.class)
//Integration Testing With @SpringBootTest
@SpringBootTest
class SpringMockOperationServiceImplTest {
    
    @Autowired
    OperationServiceImpl operationServiceImpl;
    
    // Spring Boot's @MockBean Annotation
    //We can use the @MockBean to add mock objects to the Spring application context
    @MockBean
    OperationRepository operationRepositoryMock;
    
    @MockBean
    CompteBancaireRepository compteBancaireRepositoryMock;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @BeforeEach
    void setUp() {
        
        CompteBancaire compteBancaire = new CompteBancaire();
        compteBancaire.setSolde(1000d);
    
        Operation operation = new Operation();
        operation.setCompteBancaire(compteBancaire);
        //ici on utilise le mock de compteRepository pour retourner un compteBancaire
        //quand on appelle la méthode findById de compteRepository
        //on utilise anyInt() pour dire que l'argument de la méthode peut être n'importe quel entier
        //on utilise any(Compte.class) pour dire que l'argument de la méthode peut être n'importe quel compte
        when(compteBancaireRepositoryMock.findById(anyInt()))
                .thenReturn(compteBancaire);
        when(compteBancaireRepositoryMock.save(any(CompteBancaire.class)))
                .thenReturn(compteBancaire);
        when(operationRepositoryMock.save(any(Operation.class)))
                .thenReturn(operation);
    }
    
    
    @Test
    void should_retrouver_solde_1100_car_depot_de_100_avec_mise_de_depart_1000(){
        OperationDto depot = operationServiceImpl.depot(100d);
        //vérifie que la méthode findById a été appelée 1 fois
        verify(compteBancaireRepositoryMock ,times(1)).findById(anyInt());
    
        assertNotNull(depot);
        assertEquals(depot.getCompteBancaireSolde(), 1100d, 0.001, "le solde doit être 1100");
    }
    
    @Test
    void should_retrouver_solde_900_car_retrait_de_100_avec_mise_de_depart_1000(){
        OperationDto depot = operationServiceImpl.retrait(100d);
        assertNotNull(depot);
        assertEquals(depot.getCompteBancaireSolde(), 900d, 0.001, "le solde doit être 900");
    }
    
}