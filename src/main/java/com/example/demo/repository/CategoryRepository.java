package com.example.demo.repository;

import com.example.demo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


public interface CategoryRepository extends JpaRepository<Category, String> {

    List<Category> findAllByDeletedOrderByCreatedDateDesc(Integer deleted);

    @Modifying
    @Transactional
    @Query(value = """
       UPDATE CATEGORIES
       SET DELETED = CASE WHEN :deleted = 1 THEN 1 ELSE 0 END
       WHERE CATEGORIES.CATEGORY_ID = :id
      """, nativeQuery = true)
    void updateDeleted(@Param("deleted") Integer deleted, @Param("id") String id);
}
