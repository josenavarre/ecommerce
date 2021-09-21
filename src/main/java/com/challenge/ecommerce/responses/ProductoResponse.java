package com.challenge.ecommerce.responses;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoResponse {

    private UUID uuid;
    private String nombre;
    private String sku;
    private String descripcion;
    private Long precio;
}
