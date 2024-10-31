package org.example.task_4.dto;

import lombok.extern.slf4j.Slf4j;
import org.example.task_4.db.entity.Product;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ProductsResponse {
    private List<ProductDTO> productsList;
    public ProductsResponse(List<Product> productsList) {
        this.productsList = new ArrayList<>();
        productsList.forEach(product -> this.productsList.add(new ProductDTO(product.getId(),
                product.getAccNum()
                , product.getSum()
                , ProductTypeDTO.valueOf(product.getType().getName())
                , product.getUser().getId())));
        log.info(String.valueOf(this.productsList));
    }

}
