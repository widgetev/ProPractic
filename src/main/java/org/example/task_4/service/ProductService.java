package org.example.task_4.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.example.task_4.dto.ProductDTO;
import org.example.task_4.db.entity.Product;
import org.example.task_4.db.repository.ProductRepository;
import org.example.task_4.mapper.ProductMapper;
import org.example.task_4.mapper.ReversProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ReversProductMapper reversProductMapper;

    public ProductService(ProductRepository productRepository,ProductMapper productMapper, ReversProductMapper reversProductMapper) {
        this.productRepository = productRepository;
        this.reversProductMapper = reversProductMapper;
        this.productMapper = productMapper;
    }

    public ProductDTO create(ProductDTO productDTO) {
        return reversProductMapper.map(
                                    productRepository.save(
                                            productMapper.map(productDTO)));

    }
    public ProductDTO get(Long id) {
        return reversProductMapper.map(productRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }
    public List<ProductDTO> getByUserId(Long id) {
        log.info( "Call getByUserId id = " + id);
        return reversProductMapper.mapList(productRepository.findAllByUserId(id));
    }
    public List<ProductDTO> getByAccnum(Long uid, String accnum) {
        log.info( "Call getByAccNum = " + accnum);
        return reversProductMapper.mapList(productRepository.findAllByUserIdAndAccNum(uid, accnum));
    }

    public ProductDTO getByProductIdUserId(Long pid, Long uid) {
        log.info( "Call getByProductIdUserId id = " + uid);
        return reversProductMapper.map(productRepository.findByIdAndUserId(pid,uid).orElseThrow(EntityNotFoundException::new));
    }
    public List<ProductDTO> getAll() {
        return reversProductMapper.mapList(Optional.of(productRepository.findAll()));
    }

    public ProductDTO update(ProductDTO productDTO){
        log.info( "update ProductDTO : {}  ", productDTO);
        Product product = productMapper.map(productDTO);
        return reversProductMapper.map(Optional.of(productRepository.save(product)).orElseThrow(EntityNotFoundException::new));
    }

}
