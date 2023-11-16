package com.example.katabanquespringboot.dto;

import com.example.katabanquespringboot.enums.TypeOperation;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class OperationDto {
    
    private int id;
    
    private TypeOperation type;
    
    private Double montant;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;
    
    @JsonProperty("solde")
    private Double compteBancaireSolde;
    
    @JsonProperty("client")
    private String compteBancaireClientNom;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public TypeOperation getType() {
        return type;
    }
    
    public void setType(TypeOperation type) {
        this.type = type;
    }
    
    public Double getMontant() {
        return montant;
    }
    
    public void setMontant(Double montant) {
        this.montant = montant;
    }
    
    public LocalDateTime getDate() {
        return date;
    }
    
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    
    public String getCompteBancaireClientNom() {
        return compteBancaireClientNom;
    }
    
    public void setCompteBancaireClientNom(String compteBancaireClientNom) {
        this.compteBancaireClientNom = compteBancaireClientNom;
    }
    
    public Double getCompteBancaireSolde() {
        return compteBancaireSolde;
    }
    
    public void setCompteBancaireSolde(Double compteBancaireSolde) {
        this.compteBancaireSolde = compteBancaireSolde;
    }
}
