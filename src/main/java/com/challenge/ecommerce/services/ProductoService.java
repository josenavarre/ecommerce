package com.challenge.ecommerce.services;

import com.challenge.ecommerce.entities.Producto;
import com.challenge.ecommerce.repositories.ProductoRepository;
import com.challenge.ecommerce.requests.ProductoRequest;
import com.challenge.ecommerce.responses.ProductoResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<ProductoResponse> findAllProductos(){

        List<Producto> productos = new ArrayList<>();
        List<ProductoResponse> productoResponseList = new ArrayList<>();
        ProductoResponse response = new ProductoResponse();

        productos = productoRepository.findAll();

        for (Producto producto : productos) {
            response.setUuid(producto.getUuid());
            response.setNombre(producto.getNombre());
            response.setDescripcion(producto.getDescripcion());
            response.setPrecio(producto.getPrecio());
            response.setSku(producto.getSku());
            productoResponseList.add(response);
        }

        return productoResponseList;
    }

    public ResponseEntity addProducto(Producto producto){
        producto.setNombre(producto.getNombre());
        producto.setDescripcion(producto.getDescripcion());
        producto.setPrecio(producto.getPrecio());
        producto.setSku(producto.getSku());
        return ResponseEntity.ok(productoRepository.save(producto));
    }

    public void updateProducto(ProductoRequest productoRequest, UUID uuid) {

        Optional<Producto> producto = productoRepository.findById(uuid);
        if(producto.isPresent()) {
            producto.get().setNombre(productoRequest.getNombre());
            producto.get().setDescripcion(productoRequest.getDescripcion());
            producto.get().setPrecio(productoRequest.getPrecio());
            producto.get().setSku(productoRequest.getSku());
            productoRepository.save(producto.get());
        }
        else {
            throw new RuntimeException("No se encontró un producto con el identificador " + uuid + ".");
        }
    }

}
