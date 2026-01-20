package com.example.demo.repository;

import com.example.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface EmployeeRepository extends JpaRepository<Employee, String> {

    List<Employee> findAllByDeletedOrderByCreatedDateDesc(Integer deleted);

    @Modifying
    @Transactional
    @Query(value = """
       UPDATE EMPLOYEES
       SET DELETED = CASE WHEN :deleted = 1 THEN 1 ELSE 0 END
       WHERE EMPLOYEES.EMPLOYEE_ID = :id
      """, nativeQuery = true)
    void updateDeleted(@Param("deleted") Integer deleted, @Param("id") String id);
}
