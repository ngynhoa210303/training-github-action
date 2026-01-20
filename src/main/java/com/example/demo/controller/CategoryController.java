package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.request.CategoryRequest;
import com.example.demo.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "*")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping("/view")
    public List<Category> getAll() {
        return categoryService.getList();
    }

    @GetMapping("/detail/{ma}")
    public Category detail(@PathVariable("ma") String ma, Model model) {
        return categoryService.findById(ma);
    }

    @PostMapping("/add")
    public Category create(@RequestBody CategoryRequest request) {
        return categoryService.insert(request);
    }

    @PutMapping("/update")
    public Category update(@RequestBody CategoryRequest request) {
        return categoryService.update(request.getCategoryId(), request);
    }

    @DeleteMapping("/delete/{categoryId}")
    public boolean delete(@PathVariable("categoryId") String categoryId) {
        return categoryService.delete(categoryId);
    }
}
