package com.challenge.ecommerce.repositories;

import com.challenge.ecommerce.entities.Producto;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, UUID> {

}
