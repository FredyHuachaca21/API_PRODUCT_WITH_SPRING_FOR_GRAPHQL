package com.fredgar.pe.controller;

import com.fredgar.pe.model.Product;
import com.fredgar.pe.record.ProductoInputRecord;
import com.fredgar.pe.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ProductController {

  private final ProductoService service;

  @QueryMapping
  public Optional<Product> getProductById(@Argument String id) {
    return service.getProductById(id);
  }

  @QueryMapping
  public List<Product> getAllProducts() {
    return service.getAllProducts();
  }

  @MutationMapping
  public Product crearProducto(@Argument("productInputRecord") ProductoInputRecord productoInputRecord) {
    return service.crearProducto(productoInputRecord);
  }

  @MutationMapping
  public Product updateProduct(@Argument("id") String id, @Argument("productoInputRecord") ProductoInputRecord productoInputRecord) {
    return service.actualizarProducto(id, productoInputRecord);
  }

  @MutationMapping
  public Boolean deleteProductById(@Argument String id){
    return service.deleteProductById(id);
  }
}
