package com.fredgar.pe.service;

import com.fredgar.pe.input.ProductInput;
import com.fredgar.pe.model.Product;
import com.fredgar.pe.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository repository;
  @Override
  public Optional<Product> getProductById(String id) {
    return repository.findById(id);
  }

  @Override
  public List<Product> getAllProducts() {
    return repository.findAll();
  }

  @Override
  public Product saveProduct(ProductInput productInput) {
    Product productDB = new Product();
    productDB.setName(productInput.getName());
    return repository.save(productDB);
  }

  @Override
  public Product updateProduct(String id, ProductInput productInput) {
    return repository.findById(id)
        .map(productDB -> {
          productDB.setName(productInput.getName());
          return repository.save(productDB);
        } ).orElseThrow();
  }


  @Override
  public Boolean deleteProductById(String id) {
    Optional<Product> optionalProduct = repository.findById(id);
    optionalProduct.ifPresent(product -> repository.deleteById(product.getId()));
    return optionalProduct.isPresent();
  }
}
