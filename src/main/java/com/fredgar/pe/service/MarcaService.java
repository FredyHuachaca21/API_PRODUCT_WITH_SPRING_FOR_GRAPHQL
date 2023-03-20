package com.fredgar.pe.service;

import com.fredgar.pe.input.MarcaInput;
import com.fredgar.pe.model.Marca;

import java.util.List;
import java.util.Optional;

public interface MarcaService {
  Optional<Marca> getMarcaById(String id);
  List<Marca> getAllMarcas();
  Marca saveMarca(MarcaInput marcaInput);
  Marca updateMarca(String id, MarcaInput marcaInput);
  Boolean deleteMarcaById(String id);

}
