package com.example.katabanquespringboot.web.controllers;

import com.example.katabanquespringboot.dto.OperationDto;
import com.example.katabanquespringboot.enums.TypeOperation;
import com.example.katabanquespringboot.service.impl.OperationServiceImpl;
import com.example.katabanquespringboot.web.controller.OperationController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringJUnit4ClassRunner.class) is replaced by junit5 @ExtendWith(SpringExtension.class)
@ExtendWith(SpringExtension.class)
// annotation @WebMvcTest is for Unit Testing for web layer in Spring Boot
@WebMvcTest(OperationController.class)
class OperationControllerTest {

    //@WebMvcTest Annotation can be used for a Spring MVC test that focuses only on Spring MVC components.
    //MockMvc provides support for Spring MVC testing. It encapsulates all web application beans and makes them available for testing.
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private OperationServiceImpl operationServiceMock;
    
    @BeforeEach
    void setUp() {
        OperationDto depot = new OperationDto();
        depot.setCompteBancaireClientNom("Dupont");
        depot.setCompteBancaireSolde(100d);
        depot.setType(TypeOperation.DEPOT);
        depot.setMontant(100d);
        depot.setDate(LocalDateTime.now());
    
        when(operationServiceMock.depot(100d))
                .thenReturn(depot);
        
        OperationDto retrait = new OperationDto();
        retrait.setCompteBancaireClientNom("Dupont");
        retrait.setCompteBancaireSolde(0d);
        retrait.setType(TypeOperation.RETRAIT);
        retrait.setMontant(100d);
        retrait.setDate(LocalDateTime.now());
        when(operationServiceMock.retrait(100d))
                .thenReturn(retrait);
    }

    @Test
    void shoud_getStatusOK_when_call_allOperationsAPI() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/operations")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk()); //statut 200 (HTTP status code)
    }
    
    @Test
    void shoud_getStatus_isCreated_when_call_depot_with_content() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/depot")
                        .content("{\"montant\":100}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated()) //created == 201 (HTTP status code)
                .andExpect(MockMvcResultMatchers.jsonPath("$.solde").value(100));
    }
    
    
    @Test
    void shoud_getStatus_isCreated_when_call_retrait_with_content() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/retrait")
                        .content("{\"montant\":100}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.solde").value(0));
    }
    
    
    
}