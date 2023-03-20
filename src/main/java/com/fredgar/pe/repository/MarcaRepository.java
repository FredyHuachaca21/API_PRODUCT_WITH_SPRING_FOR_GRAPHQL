package com.fredgar.pe.repository;

import com.fredgar.pe.model.Marca;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MarcaRepository extends MongoRepository<Marca, String> {
}
