package com.fredgar.pe.service.logic;

import com.fredgar.pe.input.ProductInput;
import com.fredgar.pe.model.Product;
import com.fredgar.pe.repository.ProductRepository;
import com.fredgar.pe.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductoService {

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
    productDB.setNombre(productInput.getNombre());
    productDB.setDescripcion(productInput.getDescripcion());
    productDB.setPrecio(productInput.getPrecio());
    productDB.setStock(productInput.getStock());
    productDB.setUnidadMedida(productInput.getUnidadMedida());
    productDB.setMoneda(productInput.getMoneda());
    productDB.setFechaIngreso(LocalDate.now().toString());
    productDB.setFechaActualizacion(LocalDate.now().toString());
    productDB.setMarcaId(productInput.getMarcaId());
    productDB.setCategoriaId(productInput.getCategoriaId());
    return repository.save(productDB);
  }

  @Override
  public Product updateProduct(String id, ProductInput productInput) {
    return repository.findById(id)
        .map(productDB -> {
          productDB.setNombre(productInput.getNombre());
          productDB.setDescripcion(productInput.getDescripcion());
          productDB.setPrecio(productInput.getPrecio());
          productDB.setStock(productInput.getStock());
          productDB.setUnidadMedida(productInput.getUnidadMedida());
          productDB.setMoneda(productInput.getMoneda());
          productDB.setFechaActualizacion(productInput.getFechaActualizacion());
          productDB.setMarcaId(productInput.getMarcaId());
          productDB.setCategoriaId(productInput.getCategoriaId());
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
