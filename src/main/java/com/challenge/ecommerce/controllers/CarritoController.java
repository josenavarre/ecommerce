package com.challenge.ecommerce.controllers;


import com.challenge.ecommerce.requests.ProductoCompradoRequest;
import com.challenge.ecommerce.services.CarritoService;
import com.challenge.ecommerce.services.ProductosCarritoService;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private ProductosCarritoService productosCarritoService;

    @Autowired
    private CarritoService carritoService;

    @GetMapping(path = "/iniciar", produces = "application/json")
    public ResponseEntity tomarCarrito(){
        return ResponseEntity.ok(carritoService.tomarCarrito());
    }

    @PostMapping(path = "/agregar", produces = "application/json")
    public ResponseEntity addProductoCarrito(@Valid @RequestBody ProductoCompradoRequest productoCompradoRequest){
        return ResponseEntity.ok(productosCarritoService.addProductoCompradoCarrito(productoCompradoRequest));
    }

    @PostMapping(path = "/modificar", produces = "application/json")
    public ResponseEntity modifyProductoCarrito(@Valid @RequestBody ProductoCompradoRequest productoCompradoRequest){
        return ResponseEntity.ok(productosCarritoService.modifyProductoCompradoCarrito(productoCompradoRequest));
    }

    @PostMapping(path = "/eliminar", produces = "application/json")
    public ResponseEntity deleteProductoCarrito(@Valid @RequestParam UUID productoUuid, @RequestParam UUID carritoUuid){
        return ResponseEntity.ok(productosCarritoService.deleteProductoCompradoCarrito(productoUuid, carritoUuid));
    }

    @GetMapping(path = "/checkout", produces = "application/json")
    public ResponseEntity checkoutCarrito(@Valid @RequestParam(value = "uuid") UUID uuid) {
        return ResponseEntity.ok(productosCarritoService.checkoutProductosCarrito(uuid));
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity listarProductosCarrito(@Valid @RequestParam(value = "uuid") UUID uuid) {
        return ResponseEntity.ok(productosCarritoService.findAllByCarrito(uuid));
    }
}
