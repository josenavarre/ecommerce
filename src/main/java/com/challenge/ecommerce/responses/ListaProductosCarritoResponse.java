package com.challenge.ecommerce.responses;

import com.challenge.ecommerce.entities.ProductoComprado;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListaProductosCarritoResponse {
    List<ProductoComprado> productosComprados;
    UUID uuidCarrito;
}
