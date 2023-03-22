package com.fredgar.pe.service;

import com.fredgar.pe.model.Product;
import com.fredgar.pe.input.ProductoInputRecord;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
  Optional<Product> getProductById(String id);
  List<Product> getAllProducts();
  Boolean deleteProductById(String id);
  Product crearProducto(ProductoInputRecord productoInputRecord);
  Product actualizarProducto(String id, ProductoInputRecord productoInputRecord);

}
