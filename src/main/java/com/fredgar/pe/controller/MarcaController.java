package com.fredgar.pe.controller;

import com.fredgar.pe.input.MarcaInput;
import com.fredgar.pe.model.Marca;
import com.fredgar.pe.service.MarcaService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.Arguments;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MarcaController {

  private final MarcaService service;

  @QueryMapping
  public Optional<Marca> getMarcaById(@Argument String id) {
    return service.getMarcaById(id);
  }

  @QueryMapping
  public List<Marca> getAllMarcas() {
    return service.getAllMarcas();
  }

  @MutationMapping
  public Marca saveMarca(@Argument MarcaInput marcaInput) {
    return service.saveMarca(marcaInput);
  }

  @MutationMapping
  public Marca updateMarca(@Arguments String id, MarcaInput marcaInput) {
    return service.updateMarca(id, marcaInput);
  }

  @MutationMapping
  public Boolean deleteMarcaById(@Argument String id){
    return service.deleteMarcaById(id);
  }
}
