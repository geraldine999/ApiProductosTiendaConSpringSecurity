package com.example.apiproductostiendaconspringsecurity.producto;

import com.example.apiproductostiendaconspringsecurity.producto.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Integer> {
     List<ProductoEntity> findProductoEntityByNombreContaining(String nombre);
}
