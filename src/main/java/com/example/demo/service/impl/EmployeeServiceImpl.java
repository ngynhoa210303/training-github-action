package com.example.demo.service.impl;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.request.EmployeeRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getList() {
        return employeeRepository.findAllByDeletedOrderByCreatedDateDesc(1);
    }

    public Employee insert(EmployeeRequest request) {
        Employee emp = new Employee();
        BeanUtils.copyProperties(request, emp, "employeeId");
        emp.setCreatedDate(LocalDate.from(LocalDateTime.now()));
        emp.setDeleted(1);
        emp.setStatus((short) 1);
        return employeeRepository.save(emp);
    }

    public boolean delete(String id) {
        employeeRepository.updateDeleted(0,id);
        return true;
    }

    public Employee update(String ma, EmployeeRequest request) {
        Optional<Employee> emp = employeeRepository.findById(ma);
        BeanUtils.copyProperties(request, emp.get());
        return employeeRepository.save(emp.get());
    }

    public Employee findById(String ma) {
        return employeeRepository.findById(ma).orElse(null);
    }

}
