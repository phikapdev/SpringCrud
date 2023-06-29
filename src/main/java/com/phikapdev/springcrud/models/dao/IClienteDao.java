package com.phikapdev.springcrud.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.phikapdev.springcrud.models.entity.Cliente;

public interface IClienteDao extends JpaRepository<Cliente , Long> {
    
}
