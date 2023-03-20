package com.fredgar.pe.input;

import com.fredgar.pe.enums.NombreCategoria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaInput {

  private NombreCategoria nombre;
  private String descripcion;
  private String fechaCreacion;
  private String fechaActualizacion;
}
