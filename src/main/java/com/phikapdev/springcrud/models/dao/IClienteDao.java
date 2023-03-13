package com.phikapdev.springcrud.models.dao;

import org.springframework.data.repository.CrudRepository;
import com.phikapdev.springcrud.models.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente , Long> {
    
}
