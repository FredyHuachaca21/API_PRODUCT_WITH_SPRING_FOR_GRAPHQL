package com.fredgar.pe.model;

import com.fredgar.pe.enums.Moneda;
import com.fredgar.pe.enums.UnidadMedida;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Product {

  @Id
  private String id;
  private String nombre;
  private String descripcion;
  private BigDecimal precio;
  private Integer stock;
  private UnidadMedida unidadMedida;
  private Moneda moneda;
  private String fechaCreacion;
  private String fechaActualizacion;
  @DBRef
  private Marca marca;
  @DBRef
  private Categoria categoria;
}
