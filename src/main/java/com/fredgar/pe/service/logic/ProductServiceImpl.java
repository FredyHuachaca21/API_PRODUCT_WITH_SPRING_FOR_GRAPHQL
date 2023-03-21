package com.fredgar.pe.service.logic;

import com.fredgar.pe.model.Categoria;
import com.fredgar.pe.model.Marca;
import com.fredgar.pe.model.Product;
import com.fredgar.pe.record.ProductoInputRecord;
import com.fredgar.pe.repository.CategoriaRepository;
import com.fredgar.pe.repository.MarcaRepository;
import com.fredgar.pe.repository.ProductRepository;
import com.fredgar.pe.service.CategoriaService;
import com.fredgar.pe.service.MarcaService;
import com.fredgar.pe.service.ProductoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    String marcaId = productoInputRecord.marcaId();
    String categoriaId = productoInputRecord.categoriaId();

//    Marca marca = marcaRepository.findById(marcaId).orElseThrow(() -> new RuntimeException("Marca no encontrada"));
    Marca marca = marcaService.getMarcaById(marcaId).orElseThrow(() -> new RuntimeException("Marca no encontrada"));
    log.info("Marca recuperada: {}", marca);
//    Categoria categoria = categoriaRepository.findById(categoriaId).orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
    Categoria categoria = categoriaService.getCategoriaById(categoriaId).orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
    log.info("Categoria recuperada: {}", categoria);

    Product product = new Product();
    product.setNombre(productoInputRecord.nombre());
    product.setDescripcion(productoInputRecord.descripcion());
    product.setPrecio(productoInputRecord.precio());
    product.setStock(productoInputRecord.stock());
    product.setUnidadMedida(productoInputRecord.unidadMedida());
    product.setMoneda(productoInputRecord.moneda());
    product.setFechaCreacion(LocalDateTime.now().toString());
    product.setFechaActualizacion(LocalDateTime.now().toString());
    product.setMarca(marca);
    product.setCategoria(categoria);

    Product productDB = repository.save(product);
    log.info("Producto guardado: {}", productDB);
    if (productDB == null) {
      throw new RuntimeException("Error al guardar el producto");
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
