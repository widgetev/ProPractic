package org.example.task_4.db.repository;

import org.example.task_4.db.entity.ProductsType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductsType, String> {
}
