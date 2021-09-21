package com.challenge.ecommerce.controllers;

import com.challenge.ecommerce.entities.Producto;
import com.challenge.ecommerce.requests.ProductoRequest;
import com.challenge.ecommerce.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping(path = "/nuevo", produces = "application/json")
    public ResponseEntity agregarProducto(@RequestBody ProductoRequest productoRequest) {
        Producto producto = new Producto();
        producto.setNombre(productoRequest.getNombre());
        producto.setDescripcion(productoRequest.getDescripcion());
        producto.setPrecio(productoRequest.getPrecio());
        producto.setSku(productoRequest.getSku());
        return ResponseEntity.ok(productoService.addProducto(producto));
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity listarProductos(){
        return ResponseEntity.ok(productoService.findAllProductos());
    }
}
