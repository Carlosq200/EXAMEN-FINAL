package com.multipedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.multipedidos.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {}
