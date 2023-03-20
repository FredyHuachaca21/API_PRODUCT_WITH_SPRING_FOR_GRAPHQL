package com.fredgar.pe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Marca {
  @Id
  private String id;
  private String nombre;
  private String descripcion;
  private String fechaCreacion;
  private String fechaActualizacion;
}
