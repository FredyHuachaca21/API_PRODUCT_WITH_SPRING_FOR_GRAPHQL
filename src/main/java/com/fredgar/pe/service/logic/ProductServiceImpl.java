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

    String marcaId = productoInputRecord.marcaId();
    String categoriaId = productoInputRecord.categoriaId();

    Optional<Marca> marca = marcaService.getMarcaById(marcaId);
    log.info("Marca recuperada: {}", marca);

    Optional<Categoria> categoria = categoriaService.getCategoriaById(categoriaId);
    log.info("Categoria recuperada: {}", categoria);

    Product product = new Product();
    product.setNombre(productoInputRecord.nombre());
    product.setDescripcion(productoInputRecord.descripcion());
    product.setPrecio(BigDecimal.valueOf(productoInputRecord.precio().doubleValue()));
    product.setStock(productoInputRecord.stock());
    product.setUnidadMedida(productoInputRecord.unidadMedida());
    product.setMoneda(productoInputRecord.moneda());
    product.setFechaCreacion(LocalDateTime.now().toString());
    product.setFechaActualizacion(LocalDateTime.now().toString());
    product.setMarca(marca.orElseThrow(() -> GraphqlErrorException.newErrorException()
        .message("Marca no encontrada")
        .errorClassification(NOT_FOUND)
        .build()));
    product.setCategoria(categoria.orElseThrow(() -> GraphqlErrorException.newErrorException()
        .message("Categoria no encontrada")
        .errorClassification(NOT_FOUND)
        .build()));

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
    Product product = repository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));

    String marcaId = productoInputRecord.marcaId();
    String categoriaId = productoInputRecord.categoriaId();

    Marca marca = marcaRepository.findById(marcaId).orElseThrow(() -> new RuntimeException("Marca no encontrada"));
    Categoria categoria = categoriaRepository.findById(categoriaId).orElseThrow(() -> new RuntimeException("Categoria no encontrada"));

    product.setNombre(productoInputRecord.nombre());
    product.setDescripcion(productoInputRecord.descripcion());
    product.setPrecio(productoInputRecord.precio());
    product.setStock(productoInputRecord.stock());
    product.setUnidadMedida(productoInputRecord.unidadMedida());
    product.setMoneda(productoInputRecord.moneda());
    product.setFechaActualizacion(productoInputRecord.fechaActualizacion());
    product.setMarca(marca);
    product.setCategoria(categoria);

    return repository.save(product);
  }


}
