package com.fredgar.pe.model;

import com.fredgar.pe.enums.Moneda;
import com.fredgar.pe.enums.UnidadMedida;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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
//  @DBRef

  @Field(name = "marca")
  private Marca marca;

  @Field(name = "categoria")
  private Categoria categoria;
}
