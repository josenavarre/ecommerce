package com.challenge.ecommerce.entities;

import com.challenge.ecommerce.enums.ProductoTipoEnum;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "producto")
@Data
public class Producto {
    @Id
    @GeneratedValue
    private UUID uuid;
    private String nombre;
    private String sku;
    private String descripcion;
    private Long precio;
    private ProductoTipoEnum tipo;
}
