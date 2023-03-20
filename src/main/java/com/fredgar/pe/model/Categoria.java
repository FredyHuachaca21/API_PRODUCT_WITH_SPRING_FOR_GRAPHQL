package com.fredgar.pe.model;

import com.fredgar.pe.enums.NombreCategoria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Categoria {
  @Id
  private String id;
  private NombreCategoria nombre;
  private String descripcion;
  private String fechaCreacion;
  private String fechaActualizacion;
}
