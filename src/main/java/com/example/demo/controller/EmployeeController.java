package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.entity.Employee;
import com.example.demo.request.CategoryRequest;
import com.example.demo.request.EmployeeRequest;
import com.example.demo.service.impl.CategoryServiceImpl;
import com.example.demo.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "*")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @GetMapping("/view")
    public List<Employee> getAll() {
        return employeeService.getList();
    }

    @GetMapping("/detail/{ma}")
    public Employee detail(@PathVariable("ma") String ma, Model model) {
        return employeeService.findById(ma);
    }

    @PostMapping("/add")
    public Employee create(@RequestBody EmployeeRequest request) {
        return employeeService.insert(request);
    }

    @PutMapping("/update")
    public Employee update(@RequestBody EmployeeRequest request) {
        return employeeService.update(request.getEmployeeId(), request);
    }

    @DeleteMapping("/delete/{employeeId}")
    public boolean delete(@PathVariable("employeeId") String employeeId) {
        return employeeService.delete(employeeId);
    }
}
