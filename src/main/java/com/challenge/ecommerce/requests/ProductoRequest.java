package com.challenge.ecommerce.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoRequest {

    private String nombre;
    private String sku;
    private String descripcion;
    private Long precio;
}
