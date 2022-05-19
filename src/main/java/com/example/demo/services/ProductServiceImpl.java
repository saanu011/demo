package com.example.demo.services;

import com.example.demo.models.Product;
import com.example.demo.models.User;
import com.example.demo.models.dto.ProductDto;
import com.example.demo.models.dto.UserDto;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.utils.BeanMapper;
import com.example.demo.utils.CommonUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;

    public Page<ProductDto> index(Pageable pageable) {

        return productRepository.findAll(pageable).map(this::convertProductToDto);
    }

    public ProductDto create(ProductDto productDto) {
        Product product = new Product();
        CommonUtil.copyNonNullProperties(productDto, product);
        productRepository.save(product);
        return convertProductToDto(product);
    }


    private ProductDto convertProductToDto(Product product) {
        BeanMapper mapper = new BeanMapper();
        return mapper.map(product, ProductDto.class);
    }
}
