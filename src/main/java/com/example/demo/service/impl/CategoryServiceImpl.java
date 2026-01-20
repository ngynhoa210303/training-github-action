package com.example.demo.service.impl;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.request.CategoryRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl{

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getList() {
        return categoryRepository.findAllByDeletedOrderByCreatedDateDesc(1);
    }

    public Category insert(CategoryRequest request) {
        Category categories = new Category();
        BeanUtils.copyProperties(request, categories, "categoryId");
        categories.setCreatedDate(LocalDateTime.now());
        categories.setDeleted(1);
        categories.setStatus((short) 1);
        return categoryRepository.save(categories);
    }

    public boolean delete(String id) {
        categoryRepository.updateDeleted(0,id);
        return true;
    }

    public Category update(String ma, CategoryRequest request) {
        Optional<Category> categories = categoryRepository.findById(ma);
        BeanUtils.copyProperties(request, categories.get());
        return categoryRepository.save(categories.get());
    }

    public Category findById(String ma) {
        return categoryRepository.findById(ma).orElse(null);
    }


}
