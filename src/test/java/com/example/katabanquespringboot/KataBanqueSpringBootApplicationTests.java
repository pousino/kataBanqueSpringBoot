package com.example.katabanquespringboot;

import com.example.katabanquespringboot.dto.OperationDto;
import com.example.katabanquespringboot.enums.TypeOperation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KataBanqueSpringBootApplicationTests {
    
    @LocalServerPort
    private int port;
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @AfterEach
    public void tearDown() throws Exception {
    }
    
    //renommer le fichier sql en data.sql pour jouer ce test
    @Disabled
    @Test
    public void allOperations() throws Exception {
        ResponseEntity<OperationDto[]> response =
                restTemplate.getForEntity(
                        "http://localhost:" + port + "/operations",
                        OperationDto[].class);
        OperationDto[] operations = response.getBody();
        
        assertThat(operations).isNotNull();
        assertThat(operations).hasSize(2);
        
        assertThat(operations[0].getType()).isEqualTo(TypeOperation.DEPOT);
        assertThat(operations[0].getMontant()).isEqualTo(1000d);
        
        assertThat(operations[1].getType()).isEqualTo(TypeOperation.RETRAIT);
        assertThat(operations[1].getMontant()).isEqualTo(-100d);
    }

}
