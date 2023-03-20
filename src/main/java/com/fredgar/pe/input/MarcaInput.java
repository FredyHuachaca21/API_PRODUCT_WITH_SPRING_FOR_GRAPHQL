package com.fredgar.pe.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarcaInput {
  private String nombre;
  private String descripcion;
  private String fechaCreacion;
  private String fechaActualizacion;
}
