package com.exam.examserver.service.impl;

import com.exam.examserver.entity.exam.Category;
import com.exam.examserver.entity.exam.Quiz;
import com.exam.examserver.repo.QuizRepo;
import com.exam.examserver.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepo quizRepo;

    @Override
    public Quiz addQuiz(Quiz quiz) {
        return quizRepo.save(quiz);
    }

    @Override
    public Quiz updateQuiz(Quiz updatedQuiz) {
        return quizRepo.save(updatedQuiz);
    }

    @Override
    public void deleteQuiz(Long id) {
        quizRepo.deleteById(id);
    }

    @Override
    public List<Quiz> getAllQuiz() {
        return quizRepo.findAll();
    }

    @Override
    public Quiz getQuizById(Long id) {
        return quizRepo.getById(id);
    }

    @Override
    public Set<Quiz> getQuizByCategory(Long id) {
        Category category = new Category();
        category.setCId(id);
        return quizRepo.findByCategory(category);
    }

    @Override
    public List<Quiz> findByName(String name) {
        return quizRepo.findByTitle(name);
    }
}
