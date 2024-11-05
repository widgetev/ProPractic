package org.example.task_4.db.repository;

import org.example.task_4.db.entity.ProductsType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductTypeRepository extends JpaRepository<ProductsType, String> {
    Optional<ProductsType> findByName(String s);
}
