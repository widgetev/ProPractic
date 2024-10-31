package org.example.task_4.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.example.task_4.dto.ProductDTO;
import org.example.task_4.db.entity.Product;
import org.example.task_4.db.repository.ProductRepository;
import org.example.task_4.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductService {

    private final ProductRepository productRepository;
    ProductMapper productMapper;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

//    ProductDTO create(String accnum, BigDecimal sum, ProductsType type) {
//        ProductDTO product = new ProductDTO(accnum, sum, type);
//        productDAO.save(product);
//        return product;
//    }
//    ProductDTO create(String accnum, BigDecimal sum, ProductsType type, Long userId) {
//        ProductDTO product = new ProductDTO(accnum, sum, type, userId);
//        productDAO.save(product);
//        return product;
//    }

////    void del(ProductDTO p) {
//        productDAO.delete(p);
//    }

    public Product get(Long id) {
        return productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    //return Optional.ofNullable(productDAO.get(id)).orElseThrow(EntityNotFoundException::new);
    }
    public List<Product> getByUserId(Long id) {
        log.info( "Call getByUserId id = " + id);
        return wrapList(productRepository.findAllByUserId(id));
    }
    public List<Product> getByAccnum(Long uid, String accnum) {
        log.info( "Call getByAccNum = " + accnum);
        return wrapList(productRepository.findAllByUserIdAndAccNum(uid, accnum));
    }

    public Product getByProductIdUserId(Long pid, Long uid) {
        log.info( "Call getByProductIdUserId id = " + uid);
        return productRepository.findByIdAndUserId(pid,uid).orElseThrow(EntityNotFoundException::new);
    }
    public List<Product> getAll() {
        return productRepository.findAll();
        //return wrapList(productDAO.getAll());
    }

    public Product update(ProductDTO productDTO) throws SQLException {
        log.info( "update ProductDTO : {}  ", productDTO);
        Product product = productMapper.map(productDTO);
        return Optional.of(productRepository.save(product)).orElseThrow(EntityNotFoundException::new);
    }

    List<Product> wrapList(Optional<List<Product>> productsList){
        log.info("Wrap : ");
        productsList.stream().forEach(a -> log.info(String.valueOf(a)));
        return productsList
                .filter(a -> !a.isEmpty())
                .orElseThrow(EntityNotFoundException::new);
    }
}
