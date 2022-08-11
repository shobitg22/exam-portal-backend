package com.exam.examserver.repo;

import com.exam.examserver.entity.exam.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category , Long> {
}
