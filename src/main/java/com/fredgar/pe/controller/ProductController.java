package com.fredgar.pe.controller;

import com.fredgar.pe.input.ProductInput;
import com.fredgar.pe.model.Product;
import com.fredgar.pe.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ProductController {

  private final ProductService service;

  @QueryMapping
  public Optional<Product> getProductById(@Argument String id) {
    return service.getProductById(id);
  }

  @QueryMapping
  public List<Product> getAllProducts() {
    return service.getAllProducts();
  }

  @MutationMapping
  public Product saveProduct(@Argument ProductInput productInput) {
    return service.saveProduct(productInput);
  }

  @MutationMapping
  public Product updateProduct(@Arguments String id, ProductInput productInput) {
    return service.updateProduct(id, productInput);
  }

  @MutationMapping
  public Boolean deleteProductById(@Argument String id){
    return service.deleteProductById(id);
  }
}