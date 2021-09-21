package com.challenge.ecommerce.services;

import com.challenge.ecommerce.entities.Carrito;
import com.challenge.ecommerce.enums.EstadosEnum;
import com.challenge.ecommerce.repositories.CarritoRepository;
import com.challenge.ecommerce.responses.CheckoutCarritoResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CarritoService {

    @Autowired
    CarritoRepository carritoRepository;

    public ResponseEntity tomarCarrito(){
        Carrito carrito = carritoRepository.save(new Carrito());
        return ResponseEntity.ok(carrito);
    }

    public List<Carrito> listAllCarritos(){
        List<Carrito> carritos = new ArrayList<>();
        carritos = carritoRepository.findAll();
        return carritos;
    }

    public ResponseEntity finalizarCarrito(CheckoutCarritoResponse checkoutResponse, Carrito carrito) {
        if(carrito != null){
            carrito.setEstado(checkoutResponse.getProductosComprados().get(0).getEstado());
            carrito.setTotalCompra(checkoutResponse.getTotalCompra());
            return ResponseEntity.ok(carritoRepository.save(carrito));
        }
        else {
            throw new RuntimeException("El carrito no fue encontrado");
        }
    }

    public boolean carritoActivo(UUID carritoUuid) {
        Carrito carrito = carritoRepository.findById(carritoUuid).orElse(null);
        if(carrito != null) {
        if(carrito.getEstado().isBlank() || carrito.getEstado().isEmpty() || !carrito.getEstado().equals(EstadosEnum.COMPLETADO.name())){
            return true;
        }
        else {
            return false;
        }
        }
        else throw new RuntimeException("El carrito con identificador " + carritoUuid + "no existe.");
    }
}
