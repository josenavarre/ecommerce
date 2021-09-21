package com.challenge.ecommerce.responses;

import com.challenge.ecommerce.entities.Producto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoCompradoResponse {
    private Producto producto;
    private int cantidad;
    private String estado;
}
