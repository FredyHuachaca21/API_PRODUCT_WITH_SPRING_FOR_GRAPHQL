package com.fredgar.pe.repository;

import com.fredgar.pe.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

}
