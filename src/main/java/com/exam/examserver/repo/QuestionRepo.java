package com.exam.examserver.repo;

import com.exam.examserver.entity.exam.Question;
import com.exam.examserver.entity.exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface QuestionRepo extends JpaRepository<Question,Long> {
    Set<Question> findByQuiz(Quiz quiz);
}
