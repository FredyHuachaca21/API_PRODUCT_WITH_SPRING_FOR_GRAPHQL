package com.fredgar.pe.service.logic;

import com.fredgar.pe.model.Categoria;
import com.fredgar.pe.model.Marca;
import com.fredgar.pe.model.Product;
import com.fredgar.pe.input.ProductoInputRecord;
import com.fredgar.pe.repository.CategoriaRepository;
import com.fredgar.pe.repository.MarcaRepository;
import com.fredgar.pe.repository.ProductRepository;
import com.fredgar.pe.service.CategoriaService;
import com.fredgar.pe.service.MarcaService;
import com.fredgar.pe.service.ProductoService;
import graphql.GraphqlErrorException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.graphql.execution.ErrorType.BAD_REQUEST;
import static org.springframework.graphql.execution.ErrorType.NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductoService {

  private final ProductRepository repository;
  private final CategoriaRepository categoriaRepository;
  private final MarcaRepository marcaRepository;

  private final MarcaService marcaService;
  private final CategoriaService categoriaService;

  @Override
  public Optional<Product> getProductById(String id) {
    return repository.findById(id);
  }

  @Override
  public List<Product> getAllProducts() {
    return repository.findAll();
  }



  @Override
  public Boolean deleteProductById(String id) {
    Optional<Product> optionalProduct = repository.findById(id);
    optionalProduct.ifPresent(product -> repository.deleteById(product.getId()));
    return optionalProduct.isPresent();
  }

  @Override
  @Transactional
  public Product crearProducto(ProductoInputRecord productoInputRecord) {

    if (productoInputRecord == null) {
      throw GraphqlErrorException.newErrorException()
          .message("El objeto ProductoInputRecord no puede ser nulo")
          .errorClassification(BAD_REQUEST)
          .build();
    }
    Product product = new Product();
    product.setFechaCreacion(LocalDateTime.now().toString());
    product.setFechaActualizacion(LocalDateTime.now().toString());

    setProductPropertiesFromInputRecord(product, productoInputRecord);

    Product productDB = repository.save(product);
    log.info("Producto guardado: {}", productDB);
    if (productDB == null) {
      throw GraphqlErrorException.newErrorException()
          .message("Error al guardar el producto")
          .errorClassification(BAD_REQUEST)
          .build();
    }
    return productDB;
  }

  @Override
  public Product actualizarProducto(String id, ProductoInputRecord productoInputRecord) {
    Product product = repository.findById(id).orElseThrow(() -> GraphqlErrorException.newErrorException()
        .message("Producto no encontrado con ID: " + id)
        .errorClassification(NOT_FOUND)
        .build());

    setProductPropertiesFromInputRecord(product, productoInputRecord);
    product.setFechaActualizacion(productoInputRecord.fechaActualizacion());

    return repository.save(product);
  }

  private void setProductPropertiesFromInputRecord(Product product, ProductoInputRecord productoInputRecord) {
    String marcaId = productoInputRecord.marcaId();
    String categoriaId = productoInputRecord.categoriaId();

    Marca marca = marcaService.getMarcaById(marcaId).orElseThrow(() -> GraphqlErrorException.newErrorException()
        .message("Marca no encontrada")
        .errorClassification(NOT_FOUND)
        .build());
    log.info("Marca recuperada: {}", marca);

    Categoria categoria = categoriaService.getCategoriaById(categoriaId).orElseThrow(()-> GraphqlErrorException
        .newErrorException()
        .message("Categoria no encontrada")
        .errorClassification(NOT_FOUND)
        .build());
    log.info("Categoria recuperada: {}", categoria);

    product.setNombre(productoInputRecord.nombre());
    product.setDescripcion(productoInputRecord.descripcion());
    product.setPrecio(BigDecimal.valueOf(productoInputRecord.precio().doubleValue()));
    product.setStock(productoInputRecord.stock());
    product.setUnidadMedida(productoInputRecord.unidadMedida());
    product.setMoneda(productoInputRecord.moneda());
    product.setMarca(marca);
    product.setCategoria(categoria);
  }


}
