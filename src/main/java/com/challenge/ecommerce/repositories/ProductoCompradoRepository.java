package com.challenge.ecommerce.repositories;

import com.challenge.ecommerce.entities.ProductoComprado;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductoCompradoRepository extends JpaRepository<ProductoComprado, UUID>{

    public List<ProductoComprado> findAllByCarritoUuid(UUID carritoUuid);
    @Query(value = "SELECT * FROM producto_comprado WHERE carrito_uuid = :carritoUuid AND producto_uuid = :productoUuid", nativeQuery = true)
    public List<ProductoComprado> findByUuidAndCarrito(UUID productoUuid, UUID carritoUuid);
}
