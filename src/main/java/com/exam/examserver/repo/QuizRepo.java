package com.exam.examserver.repo;

import com.exam.examserver.entity.exam.Category;
import com.exam.examserver.entity.exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface QuizRepo extends JpaRepository<Quiz,Long> {
    Set<Quiz> findByCategory(Category category);

    List<Quiz> findByTitle(String name);
}
