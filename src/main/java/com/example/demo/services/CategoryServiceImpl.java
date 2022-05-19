package com.example.demo.services;

import com.example.demo.models.Category;
import com.example.demo.models.Product;
import com.example.demo.models.dto.CategoryDto;
import com.example.demo.models.dto.ProductDto;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.utils.BeanMapper;
import com.example.demo.utils.CommonUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class CategoryServiceImpl implements CategoryService {
    CategoryRepository categoryRepository;

    public Page<CategoryDto> index(Pageable pageable) {

        return categoryRepository.findAll(pageable).map(this::convertCategoryToDto);
    }

    public CategoryDto create(CategoryDto categoryDto) {
        Category category = new Category();
        CommonUtil.copyNonNullProperties(categoryDto, category);
        categoryRepository.save(category);
        return convertCategoryToDto(category);
    }


    private CategoryDto convertCategoryToDto(Category category) {
        BeanMapper mapper = new BeanMapper();
        return mapper.map(category, CategoryDto.class);
    }
}
