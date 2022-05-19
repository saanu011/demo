package com.example.demo.services;

import com.example.demo.models.dto.CategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    Page<CategoryDto> index(Pageable pageable);

    CategoryDto create(CategoryDto categoryDto);
}
