package com.example.demo.controllers;

import com.example.demo.models.dto.CategoryDto;
import com.example.demo.services.CategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping(name = "", produces = "application/json")
    public ResponseEntity<CategoryDto> create(@RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<>(categoryService.create(categoryDto), HttpStatus.CREATED);
    }

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<Map<String, Object>> index(HttpServletRequest request) {
        Pageable pageable = getPaginationAndSortingParams(request);
        Page<CategoryDto> categoryDtoPage = categoryService.index(pageable);
        Map<String, Object> mapper = new HashMap<>();
        mapper.put("data", categoryDtoPage.getContent());
        return ResponseEntity.ok(mapper);
    }

    public Pageable getPaginationAndSortingParams(HttpServletRequest request) {
        String pageParam = request.getParameter("page");
        String sizeParam = request.getParameter("size");
        String sortParam = request.getParameter("sort");

        int page = !StringUtils.isBlank(pageParam) ? (Integer.parseInt(pageParam.trim())-1) : 0;
        int size = !StringUtils.isBlank(sizeParam) ? Integer.parseInt(sizeParam.trim()) : 10;

        Sort sort = fetchOrSetDefaultSort(sortParam);

        return PageRequest.of(page, size, sort);
    }

    private Sort fetchOrSetDefaultSort(String sortParam) {
        String[] sortParams = StringUtils.isBlank(sortParam) ? " ".split(",") : sortParam.split(",");
        String sortByParam = sortParams.length>0 ? sortParams[0].trim() : "id";
        String orderParam = sortParams.length>1 ? sortParams[1].trim() : "DESC";

        String sortBy = !StringUtils.isBlank(sortByParam) ? sortByParam : "id";
        String order = !StringUtils.isBlank(orderParam) ? orderParam : "DESC";

        return (order.toLowerCase().equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending());
    }
}

