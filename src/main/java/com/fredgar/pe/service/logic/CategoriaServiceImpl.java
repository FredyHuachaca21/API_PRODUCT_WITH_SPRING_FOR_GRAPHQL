package com.fredgar.pe.service.logic;

import com.fredgar.pe.input.CategoriaInput;
import com.fredgar.pe.model.Categoria;
import com.fredgar.pe.repository.CategoriaRepository;
import com.fredgar.pe.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

  private final CategoriaRepository repository;

  @Override
  public Optional<Categoria> getCategoriaById(String id) {
    return repository.findById(id);
  }

  @Override
  public List<Categoria> getAllCategorias() {
    return repository.findAll();
  }

  @Override
  public Categoria saveCategoria(CategoriaInput categoriaInput) {
    Categoria categoriaDB = new Categoria();
    categoriaDB.setNombre(categoriaInput.nombre());
    categoriaDB.setDescripcion(categoriaInput.descripcion());
    categoriaDB.setFechaCreacion(LocalDate.now().toString());
    categoriaDB.setFechaActualizacion(LocalDate.now().toString());
    return repository.save(categoriaDB);
  }

  @Override
  public Categoria updateCategoria(String id, CategoriaInput categoriaInput) {
    return repository.findById(id)
        .map(categoriaDB -> {
          categoriaDB.setNombre(categoriaInput.nombre());
          categoriaDB.setDescripcion(categoriaInput.descripcion());
          categoriaDB.setFechaActualizacion(categoriaInput.fechaActualizacion());
          return repository.save(categoriaDB);
        }).orElseThrow();
  }

  @Override
  public Boolean deleteCategoriaById(String id) {
    Optional<Categoria> optionalCategoria = repository.findById(id);
    optionalCategoria.ifPresent(categoria -> repository.deleteById(categoria.getId()));
    return optionalCategoria.isPresent();
  }
}
