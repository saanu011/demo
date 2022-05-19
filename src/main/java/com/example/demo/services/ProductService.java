package com.example.demo.services;

import com.example.demo.models.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Page<ProductDto> index(Pageable pageable);

    ProductDto create(ProductDto productDto);
}
