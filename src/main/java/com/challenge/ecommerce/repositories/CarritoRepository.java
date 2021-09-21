package com.challenge.ecommerce.repositories;

import com.challenge.ecommerce.entities.Carrito;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarritoRepository extends JpaRepository<Carrito, UUID> {

}
