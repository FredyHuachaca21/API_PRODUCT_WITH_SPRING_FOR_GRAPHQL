package com.fredgar.pe.input;

import com.fredgar.pe.enums.Moneda;
import com.fredgar.pe.enums.UnidadMedida;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInput {
  private String nombre;
  private String descripcion;
  private BigDecimal precio;
  private Integer stock;
  private UnidadMedida unidadMedida;
  private Moneda moneda;
  private String fechaIngreso;
  private String fechaActualizacion;
  private String marcaId;
  private String categoriaId;
}
