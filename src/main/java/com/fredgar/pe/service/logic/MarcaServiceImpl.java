package com.fredgar.pe.service.logic;

import com.fredgar.pe.input.MarcaInput;
import com.fredgar.pe.model.Marca;
import com.fredgar.pe.repository.MarcaRepository;
import com.fredgar.pe.service.MarcaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MarcaServiceImpl implements MarcaService {

  private final MarcaRepository repository;

  @Override
  public Optional<Marca> getMarcaById(String id) {
    return repository.findById(id);
  }

  @Override
  public List<Marca> getAllMarcas() {
    return repository.findAll();
  }
  @Override
  public Marca saveMarca(MarcaInput marcaInput) {
    Marca marcaDB = new Marca();
    marcaDB.setNombre(marcaInput.getNombre());
    marcaDB.setDescripcion(marcaInput.getDescripcion());
    marcaDB.setFechaCreacion(LocalDate.now().toString());
    marcaDB.setFechaActualizacion(LocalDate.now().toString());
    return repository.save(marcaDB);
  }

  @Override
  public Marca updateMarca(String id, MarcaInput marcaInput) {
    return repository.findById(id)
        .map(marcaDB -> {
          marcaDB.setNombre(marcaDB.getNombre());
          marcaDB.setDescripcion(marcaInput.getDescripcion());
          marcaDB.setFechaActualizacion(marcaInput.getFechaActualizacion());
          return repository.save(marcaDB);
        } ).orElseThrow();
  }

  @Override
  public Boolean deleteMarcaById(String id) {
    Optional<Marca> optionalMarca = repository.findById(id);
    optionalMarca.ifPresent(marca -> repository.deleteById(marca.getId()));
    return optionalMarca.isPresent();
  }
}
