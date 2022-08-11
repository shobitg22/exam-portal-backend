package com.exam.examserver.service;

import com.exam.examserver.entity.exam.Category;
import com.exam.examserver.entity.exam.Quiz;

import java.util.List;
import java.util.Set;

public interface QuizService {
    Quiz addQuiz(Quiz quiz);
    Quiz updateQuiz(Quiz updatedQuiz);
    void deleteQuiz(Long id);
    List<Quiz> getAllQuiz();
    Quiz getQuizById(Long id);
    Set<Quiz> getQuizByCategory(Long id);

    List<Quiz> findByName(String name);
}
