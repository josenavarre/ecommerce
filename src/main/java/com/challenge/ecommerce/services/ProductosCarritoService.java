package com.challenge.ecommerce.services;

import com.challenge.ecommerce.entities.Carrito;
import com.challenge.ecommerce.entities.Producto;
import com.challenge.ecommerce.entities.ProductoComprado;
import com.challenge.ecommerce.enums.EstadosEnum;
import com.challenge.ecommerce.enums.ProductoTipoEnum;
import com.challenge.ecommerce.repositories.CarritoRepository;
import com.challenge.ecommerce.repositories.ProductoCompradoRepository;
import com.challenge.ecommerce.repositories.ProductoRepository;
import com.challenge.ecommerce.requests.ProductoCompradoRequest;
import com.challenge.ecommerce.responses.CheckoutCarritoResponse;
import com.challenge.ecommerce.responses.ListaProductosCarritoResponse;
import com.challenge.ecommerce.responses.ProductoCompradoResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductosCarritoService {

    @Autowired
    private ProductoCompradoRepository productoCompradoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired CarritoService carritoService;

    public ResponseEntity addProductoCompradoCarrito(ProductoCompradoRequest productoCompradoRequest){
        ProductoComprado productoComprado = new ProductoComprado();
        Optional<Carrito> carrito = carritoRepository.findById(productoCompradoRequest.getCarritoUuid());
        Optional<Producto> producto = productoRepository.findById(productoCompradoRequest.getProductoUuid());
        if(producto.isPresent() && carrito.isPresent()) {
            productoComprado.setProducto(producto.orElse(null));
            productoComprado.setCarrito(carrito.orElse(null));
            productoComprado.setCantidad(productoCompradoRequest.getCantidad());
            productoComprado.setEstado(EstadosEnum.PENDIENTE.name());
        }
        else{
            throw new RuntimeException("Parámetros incorrectos.");
        }
        productoCompradoRepository.save(productoComprado);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity modifyProductoCompradoCarrito(ProductoCompradoRequest productoCompradoRequest){
        Optional<Carrito> carrito = carritoRepository.findById(productoCompradoRequest.getCarritoUuid());
        Optional<Producto> producto = productoRepository.findById(productoCompradoRequest.getProductoUuid());
        ProductoComprado productoComprado = productoCompradoRepository.findByUuidAndCarrito(producto.get().getUuid(), carrito.get()
                .getUuid()).get(0);

        if(productoComprado != null) {
            productoComprado.setProducto(producto.orElse(null));
            productoComprado.setCarrito(carrito.orElse(null));
            productoComprado.setCantidad(productoCompradoRequest.getCantidad());
            productoComprado.setEstado(EstadosEnum.PENDIENTE.name());
        }
        else{
            throw new RuntimeException("Parámetros incorrectos.");
        }
        productoCompradoRepository.save(productoComprado);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity deleteProductoCompradoCarrito(UUID productoUuid, UUID carritoUuid) {

        List<ProductoComprado> productosComprados = productoCompradoRepository.findByUuidAndCarrito(productoUuid, carritoUuid);
            if(Objects.nonNull(productosComprados)){
                for (ProductoComprado productoComprado: productosComprados) {
                    productoCompradoRepository.delete(productoComprado);
                    return ResponseEntity.ok().build();
                }
            }
            else {
                throw new RuntimeException("El producto comprado con el identificador "+ productoUuid + "no existe.");
            }
        return ResponseEntity.unprocessableEntity().build();
    }

    public CheckoutCarritoResponse checkoutProductosCarrito(UUID carritoUuid) {
        if(carritoService.carritoActivo(carritoUuid)){
        ListaProductosCarritoResponse listaCarrito = findAllByCarrito(carritoUuid);
        if(listaCarrito != null) {
        CheckoutCarritoResponse checkoutResponse = new CheckoutCarritoResponse();
            for (ProductoComprado productoComprado : listaCarrito.getProductosComprados()) {
                productoComprado.setEstado(EstadosEnum.COMPLETADO.name());
                productoCompradoRepository.save(productoComprado);
            }
        checkoutResponse.setProductosComprados(listaCarrito.getProductosComprados());
        checkoutResponse.setUuidCarrito(listaCarrito.getUuidCarrito());
        Long valorTotal = 0L;
        for (ProductoComprado productoComprado : listaCarrito.getProductosComprados()) {
            if(Objects.nonNull(productoComprado.getProducto().getTipo()) && productoComprado
                    .getProducto().getTipo().equals(ProductoTipoEnum.CON_DESCUENTO)){
                valorTotal = valorTotal + productoComprado.getProducto().getPrecio()/2
                        * productoComprado.getCantidad();
            }
            else {
                valorTotal = valorTotal + productoComprado.getProducto().getPrecio()
                        * productoComprado.getCantidad();

            }
        }
        checkoutResponse.setTotalCompra(valorTotal);
        Carrito carrito = carritoRepository.findById(carritoUuid).orElse(null);
            carritoService.finalizarCarrito(checkoutResponse, carrito);
            return checkoutResponse;
        }
        else {
            throw new RuntimeException("El carrito con identificador " + carritoUuid + "no existe.");
        }
        }else{
            throw new RuntimeException("Carrito ya en estado COMPLETADO. No se puede hacer checkout.");
        }
    }

    public ListaProductosCarritoResponse findAllByCarrito(UUID carritoUUID) {
        List<ProductoComprado> productosComprados = productoCompradoRepository.findAllByCarritoUuid(carritoUUID);
        ProductoCompradoResponse productoCompradoResponse = new ProductoCompradoResponse();
        ListaProductosCarritoResponse listaProductosCarritoResponse = new ListaProductosCarritoResponse();
        List<ProductoCompradoResponse> productoCompradoResponseList = new ArrayList<>();

        listaProductosCarritoResponse.setProductosComprados(productosComprados);
        System.out.println(productoCompradoResponseList);
        listaProductosCarritoResponse.setUuidCarrito(carritoUUID);

        return listaProductosCarritoResponse;
    }
}
