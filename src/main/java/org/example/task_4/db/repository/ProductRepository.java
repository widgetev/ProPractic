package org.example.task_4.db.repository;

import org.example.task_4.db.entity.Product;
import org.example.task_4.db.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<List<Product>> findAllByUserId(Long id);

//    @Query(value = "SELECT * FROM products p WHERE p.userid = ?1 AND accnum = ?2",
//            nativeQuery = true)
    Optional<List<Product>> findAllByUserIdAndAccNum(Long userId, String accnum);

    Optional<Product> findByIdAndUserId(Long pid, Long uid);

}
