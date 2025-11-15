package com.multipedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.multipedidos.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {}
