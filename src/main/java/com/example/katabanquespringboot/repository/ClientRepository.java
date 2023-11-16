package com.example.katabanquespringboot.repository;

import com.example.katabanquespringboot.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    
    Client findById(int id);
    List<Client> findByNom(String nom);


}
