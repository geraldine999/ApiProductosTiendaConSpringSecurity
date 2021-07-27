package com.example.apiproductostiendaconspringsecurity.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/productos")
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }


    @GetMapping()
    public List<ProductoEntity> listarTodosLosProductos(){
        return productoService.findAll();
    }

    @GetMapping("{producto_id}")
    public ProductoEntity listarProductoPorId(@PathVariable Integer producto_id){
        return productoService.findProductoEntityById(producto_id);
    }

    @GetMapping("{buscar/nombre}")
    public List<ProductoEntity> listarProductosQueContenganNombre(@PathVariable String nombre){
        return productoService.findByNameContaining(nombre);
    }


    @PostMapping("guardar")
    public void modificarProductoExistente(@RequestBody ProductoEntity producto){
        productoService.save(producto);
    }

    @PutMapping("guardar")
    public void guardarNuevoProducto(@RequestBody ProductoEntity producto){
             productoService.save(producto);
    }

    @DeleteMapping("{producto_id}")
    public void eliminarProductoPorId(@PathVariable Integer producto_id){
        productoService.deleteById(producto_id);
    }
}
