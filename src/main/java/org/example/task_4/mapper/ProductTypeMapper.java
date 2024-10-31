package org.example.task_4.mapper;

import org.example.task_4.db.entity.ProductsType;
import org.example.task_4.dto.ProductTypeDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductTypeMapper implements RequestBodyMapperInterface<ProductTypeDTO, ProductsType> {
    @Override
    public ProductsType map(ProductTypeDTO request) {
        ProductsType productsType = new ProductsType();
        productsType.setName(request.name());
        return productsType;
    }
}
