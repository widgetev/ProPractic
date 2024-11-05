package org.example.task_4.mapper;

import org.example.task_4.db.entity.ProductsType;
import org.example.task_4.db.repository.ProductTypeRepository;
import org.example.task_4.dto.ProductTypeDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductTypeMapper implements RequestBodyMapperInterface<ProductTypeDTO, ProductsType> {

    private final ProductTypeRepository productTypeRepository;

    public ProductTypeMapper(ProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    @Override
    public ProductsType map(ProductTypeDTO request) {
        //"законным" путем до эксепшена не дойдет, отвалится на этапе чтения запроса.
        ProductsType productsType = productTypeRepository.findByName(request.name()).orElseThrow(()->{throw new RuntimeException("Не верный ProductType :" +request.name());});
        return productsType;
    }
}
