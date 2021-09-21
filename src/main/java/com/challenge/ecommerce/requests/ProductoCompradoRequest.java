package com.challenge.ecommerce.requests;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoCompradoRequest {

    private UUID productoUuid;
    private int cantidad;
    private UUID carritoUuid;
}
