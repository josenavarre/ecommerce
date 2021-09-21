package com.challenge.ecommerce.entities;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name="producto_comprado")
@Data
public class ProductoComprado {
    @Id
    @GeneratedValue
    private UUID uuid;
    @ManyToOne
    private Producto producto;
    private int cantidad;
    private String estado;
    @ManyToOne
    private Carrito carrito;
}
