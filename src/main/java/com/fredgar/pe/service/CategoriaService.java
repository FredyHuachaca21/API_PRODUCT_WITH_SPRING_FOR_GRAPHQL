package com.fredgar.pe.service;

import com.fredgar.pe.input.CategoriaInput;
import com.fredgar.pe.model.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {
  Optional<Categoria> getCategoriaById(String id);
  List<Categoria> getAllCategorias();
  Categoria saveCategoria(CategoriaInput categoriaInput);
  Categoria updateCategoria(String id, CategoriaInput categoriaInput);
  Boolean deleteCategoriaById(String id);

}
