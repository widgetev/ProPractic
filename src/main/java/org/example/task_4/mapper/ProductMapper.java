package org.example.task_4.mapper;

import org.example.task_4.db.entity.Product;

import org.example.task_4.dto.ProductDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements RequestBodyMapperInterface<ProductDTO, Product>{

    private final ProductTypeMapper productTypeMapper;
    private final UserMapper userMapper;

    public ProductMapper(ProductTypeMapper productTypeMapper, UserMapper userMapper) {
        this.productTypeMapper = productTypeMapper;
        this.userMapper = userMapper;
    }

    @Override
    public Product map(ProductDTO request) {
        Product product = new Product();
        product.setId(request.id());
        product.setAccNum(request.accNum());
        product.setSum(request.sum());
        product.setType(productTypeMapper.map(request.type()));
        product.setUser(userMapper.mapById(request.userId()));
        return product;
    }
}
