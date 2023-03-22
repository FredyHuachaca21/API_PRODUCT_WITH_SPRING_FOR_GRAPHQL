package com.fredgar.pe.input;

import com.fredgar.pe.enums.Moneda;
import com.fredgar.pe.enums.UnidadMedida;

import java.math.BigDecimal;

public record ProductoInputRecord(
    String nombre,
    String descripcion,
    BigDecimal precio,
    Integer stock,
    UnidadMedida unidadMedida,
    Moneda moneda,
    String fechaCreacion,
    String fechaActualizacion,
    String marcaId,
    String categoriaId
) {
}
