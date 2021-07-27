package com.example.apiproductostiendaconspringsecurity.producto;

import com.example.apiproductostiendaconspringsecurity.producto.ProductoEntity;
import com.example.apiproductostiendaconspringsecurity.producto.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<ProductoEntity> findAll() {
        return productoRepository.findAll();
    }

    public ProductoEntity findProductoEntityById(Integer producto_id) {
            return productoRepository.findById(producto_id).orElse(null);
    }

    public List<ProductoEntity> findByNameContaining(String nombre) {
        return productoRepository.findProductoEntityByNombreContaining(nombre);
    }


    public void deleteById(Integer producto_id) {
         productoRepository.deleteById(producto_id);
    }

    public void save(ProductoEntity producto) {
        if (producto.getProducto_id() != null) {
            //busco a ver si existe el producto
            ProductoEntity productoExistente = productoRepository.findById(producto.getProducto_id()).orElse(null);
            //si el producto existe
            if (productoExistente != null) {
                //me fijo si los atributos pasados tienen valor, solo asi los guardo
                if (producto.getNombre() != null) productoExistente.setNombre(producto.getNombre());
                if (producto.getPrecio() != null) productoExistente.setPrecio(producto.getPrecio());
                if (producto.getStock() != null) productoExistente.setStock(producto.getStock());
                productoRepository.save(productoExistente);
            } else producto.setProducto_id(null);
        }
        //si el producto no existe, creo un nuevo producto
        productoRepository.save(producto);
    }

}
