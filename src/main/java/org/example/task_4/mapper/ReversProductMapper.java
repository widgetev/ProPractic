package org.example.task_4.mapper;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.example.task_4.db.entity.Product;
import org.example.task_4.dto.ProductDTO;
import org.example.task_4.dto.ProductTypeDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class ReversProductMapper implements RequestBodyMapperInterface<Product, ProductDTO> {

    @Override
    public ProductDTO map(Product request) {
        return new ProductDTO(request.getId(),
                request.getAccNum()
                , request.getSum()
                , ProductTypeDTO.valueOf(request.getType().getName())
                , request.getUser().getId());
    }

    public List<ProductDTO> mapList(Optional<List<Product>> optionalProductsList){
        log.info("Wrap : ");
        List<ProductDTO> productDTOList=new ArrayList<>();
        List<Product> productsList =  optionalProductsList
                .filter(a -> !a.isEmpty())
                .orElseThrow(EntityNotFoundException::new);

        productsList.forEach(product -> productDTOList.add(this.map(product)));
        return productDTOList;
    }
}
