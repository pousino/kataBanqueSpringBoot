package com.example.katabanquespringboot.repository;

import com.example.katabanquespringboot.entity.CompteBancaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteBancaireRepository extends JpaRepository<CompteBancaire,Long> {
    
    CompteBancaire findById(int id);
}
