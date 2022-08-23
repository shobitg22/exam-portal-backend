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
        list.forEach(li->{
            li.setAnswer("");
        });
        Collections.shuffle(list);
        return list;
    }
    @Override
    public Set<Question> getQuestionsByQuizForAdmin(Long id) {
        Quiz quiz = quizService.getQuizById(id);
        return questionRepo.findByQuiz(quiz);
    }

    @Override
    public Map<String, Object> evalQuestions(List<Question> questions) {
        int correctAnswer=0;
        int attemptedQuestion=0;
        int marksObtained=0;
        for(Question ques:questions){
            Question question = getQuestionById(ques.getQuesId());
            if(Objects.equals(question.getAnswer(), ques.getGivenAnswer())){
                correctAnswer++;
                int singleMarks=Integer.parseInt(questions.get(0).getQuiz().getMaxMarks()) / questions.size();
                marksObtained+=singleMarks;
            }
            if( ques.getGivenAnswer()!=null ) {
                attemptedQuestion++;
            }
        };
        Map<String,Object> map = Map.of("marksObtained",marksObtained,"attemptedQuestion",attemptedQuestion,"correctAnswer",correctAnswer);
        return map;
    }

}
