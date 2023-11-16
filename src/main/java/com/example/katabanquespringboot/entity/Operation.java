package com.example.katabanquespringboot.entity;

import com.example.katabanquespringboot.enums.TypeOperation;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@EqualsAndHashCode
@Data
@Table(name = "COMPTE_BANCAIRE")
public class Operation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull(message = "Type operation est obligatoire")
    @Enumerated(EnumType.STRING)
    private TypeOperation type;
    
    @NotNull(message = "montant est obligatoire")
    private Double montant;
    
    private LocalDateTime date;
    
    @NotNull(message = "Le compte bancaire est obligatoire")
    @ManyToOne
    private CompteBancaire compteBancaire;
    
    public Operation() {
        this.date = LocalDateTime.now();
    }
    
    public Operation(TypeOperation type, Double montant) {
        this.type = type;
        this.montant = montant;
        this.date = LocalDateTime.now();
    }
    
    
    
}
