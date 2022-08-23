package com.exam.examserver.service;

import com.exam.examserver.entity.exam.Question;
import com.exam.examserver.entity.exam.Quiz;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface QuestionService {
    Question addQuestion(Question question);
    Question updateQuestion(Question updatedQuestion);
    void deleteQuestion(Long id);
    List<Question> getAllQuestions();
    Question getQuestionById(Long id);
    List<Question> getQuestionsByQuiz(Long id);

    Map<String,Object> evalQuestions(List<Question> questions);
     Set<Question> getQuestionsByQuizForAdmin(Long id) ;


}
