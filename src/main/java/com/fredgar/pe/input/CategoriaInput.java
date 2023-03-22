package com.fredgar.pe.input;

import com.fredgar.pe.enums.NombreCategoria;

public record CategoriaInput(
    NombreCategoria nombre,
    String descripcion,
    String fechaCreacion,
    String fechaActualizacion
) {
}
