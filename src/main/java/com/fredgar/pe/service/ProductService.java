package com.fredgar.pe.service;

import com.fredgar.pe.input.ProductInput;
import com.fredgar.pe.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
  Optional<Product> getProductById(String id);
  List<Product> getAllProducts();
  Product saveProduct(ProductInput productInput);
  Product updateProduct(String id, ProductInput productInput);
  Boolean deleteProductById(String id);

}
