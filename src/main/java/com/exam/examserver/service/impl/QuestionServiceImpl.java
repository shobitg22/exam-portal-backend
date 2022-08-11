package com.exam.examserver.service.impl;

import com.exam.examserver.entity.exam.Question;
import com.exam.examserver.entity.exam.Quiz;
import com.exam.examserver.repo.QuestionRepo;
import com.exam.examserver.service.QuestionService;
import com.exam.examserver.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepo questionRepo;
    @Autowired
    private QuizService quizService;

    @Override
    public Question addQuestion(Question question) {
//        Quiz quiz=question.getQuiz();
//        quiz=quizService.getQuizById(quiz.getQId());
//        question.setQuiz(quiz);

        return questionRepo.save(question);
    }

    @Override
    public Question updateQuestion(Question updatedQuestion) {
        return questionRepo.save(updatedQuestion);
    }

    @Override
    public void deleteQuestion(Long id) {
        questionRepo.deleteById(id);
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepo.findAll();
    }

    @Override
    public Question getQuestionById(Long id) {
        return questionRepo.getById(id);
    }

    @Override
    public List<Question> getQuestionsByQuiz(Long id) {
            Quiz quiz = quizService.getQuizById(id);
        Set<Question> questionSet= questionRepo.findByQuiz(quiz);
        List<Question> list = new ArrayList<>(questionSet);
        if(list.size()>Integer.parseInt(quiz.getNumberOfQuestions())){
           list= list.subList(0,Integer.parseInt(quiz.getNumberOfQuestions()+1));
        }
        Collections.shuffle(list);
        return list;
    }
}
