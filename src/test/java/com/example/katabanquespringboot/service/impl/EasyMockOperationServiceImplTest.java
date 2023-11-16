package com.example.katabanquespringboot.service.impl;


import com.example.katabanquespringboot.dto.OperationDto;
import com.example.katabanquespringboot.entity.CompteBancaire;
import com.example.katabanquespringboot.entity.Operation;
import com.example.katabanquespringboot.repository.CompteBancaireRepository;
import com.example.katabanquespringboot.repository.OperationRepository;
import org.easymock.EasyMock;
import org.easymock.EasyMockExtension;
import org.easymock.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(EasyMockExtension.class)
//Integration Testing With @SpringBootTest
@SpringBootTest
public class EasyMockOperationServiceImplTest {
    
    @Autowired
    OperationServiceImpl operationServiceImpl;
    
    @Autowired
    ModelMapper modelMapper;
    
    @Mock
    OperationRepository operationRepositoryMock;
    //autre manière de mocker dans les méthodes operationRepositoryMock = createMock(OperationRepository.class);
    
    @Mock
    CompteBancaireRepository compteBancaireRepositoryMock;
    
    @BeforeEach
    void setUp() {
        operationServiceImpl = new OperationServiceImpl();
        operationServiceImpl.setOperationRepository(operationRepositoryMock);
        operationServiceImpl.setCompteBancaireRepository(compteBancaireRepositoryMock);
        CompteBancaire compteBancaire = new CompteBancaire();
        compteBancaire.setSolde(1000d);
        Operation operation = new Operation();
        operation.setCompteBancaire(compteBancaire);
        
        expect(compteBancaireRepositoryMock.findById(anyInt()))
                .andReturn(compteBancaire).anyTimes();
        // on enregistre le comportement du mock pour la méthode findById de compteRepository
        // en lui disant de retourner un compteBancaire quand on l'appelle avec n'importe
        // quel entier en paramètre et de le faire une seule fois (once)
        expect(compteBancaireRepositoryMock.save(isA(CompteBancaire.class)))
                .andReturn(compteBancaire).anyTimes();
        // on enregistre le comportement du mock pour la méthode save de compteRepository
        // en lui disant de retourner un compteBancaire quand on l'appelle avec n'importe
        // quel compteBancaire en paramètre et de le faire une seule fois (once)
        expect(operationRepositoryMock.save(isA(Operation.class)))
                .andReturn(operation).anyTimes();
    }
    
    
    @Test
    void should_retrouver_solde_1100_car_depot_de_100_avec_mise_de_depart_1000(){
        EasyMock.replay(compteBancaireRepositoryMock);//active le mock pour compteBancaireRepositoryMock
        EasyMock.replay(operationRepositoryMock);//active le mock pour operationRepositoryMock
        OperationDto depot = operationServiceImpl.depot(100d);
        EasyMock.verify(compteBancaireRepositoryMock); //vérifie que la méthode findById a été appelée
        EasyMock.verify(operationRepositoryMock); // vérifie que la méthode save a été appelée
        assertNotNull(depot);
        assertEquals(depot.getCompteBancaireSolde(), 1100d,"Le solde du compte bancaire doit être de 1100");
    }
    
}
