package com.challenge.ecommerce.requests;

import com.challenge.ecommerce.entities.ProductoComprado;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoRequest {

    private UUID uuid;
    private List<ProductoComprado> productosComprados;
}
