package com.example.demo.service.impl;

import com.example.demo.entity.Category;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.request.CategoryRequest;
import com.example.demo.request.ProductRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Product> getList() {
        return productRepository.findAllByDeletedOrderByCreatedDateDesc(1);
    }

    public Product insert(ProductRequest request) {
        Product product = new Product();
        BeanUtils.copyProperties(request, product, "productId", "category", "employee");
        product.setCreatedDate(LocalDateTime.now());
        product.setDeleted(1);
        if (product.getQuantity() != null && product.getQuantity() > 0) {
            product.setStatus((short) 1);
        } else {
            product.setStatus((short) 0);
        }
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy loại sản phẩm"));
        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên"));
        product.setCategory(category);
        product.setEmployee(employee);
        return productRepository.save(product);
    }

    public boolean delete(String id) {
        productRepository.updateDeleted(0,id);
        return true;
    }

    public ResponseEntity<Product> update(
            @PathVariable("id") String id,
            @RequestBody ProductRequest request) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Product existing = optionalProduct.get();
        existing.setProductName(request.getProductName());
        existing.setDescription(request.getDescription());
        existing.setPrice(request.getPrice());
        existing.setQuantity(request.getQuantity());
        existing.setStatus(request.getStatus());

        if (request.getCategoryId() != null) {
            Category cat = categoryRepository.findById(request.getCategoryId()).orElse(null);
            existing.setCategory(cat);
        }
        if (request.getEmployeeId() != null) {
            Employee emp = employeeRepository.findById(request.getEmployeeId()).orElse(null);
            existing.setEmployee(emp);
        }

        return ResponseEntity.ok(productRepository.save(existing));
    }

    public Product findById(String ma) {
        return productRepository.findById(ma).orElse(null);
    }


}
