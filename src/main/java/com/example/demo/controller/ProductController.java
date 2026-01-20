package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.request.ProductRequest;
import com.example.demo.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @GetMapping("/view")
    public List<Product> getAll() {
        return productService.getList();
    }

    @GetMapping("/detail/{ma}")
    public Product detail(@PathVariable("ma") String ma, Model model) {
        return productService.findById(ma);
    }

    @PostMapping("/add")
    public Product create(@RequestBody ProductRequest request) {
        return productService.insert(request);
    }

    @PutMapping("/update")
    public ResponseEntity<Product> update(@RequestBody ProductRequest request) {
        if (request.getProductId() == null) {
            throw new IllegalArgumentException("ProductId không được null");
        }
        return productService.update(request.getProductId(), request);
    }

    @DeleteMapping("/delete/{productId}")
    public boolean delete(@PathVariable("productId") String categoryId) {
        return productService.delete(categoryId);
    }
}
