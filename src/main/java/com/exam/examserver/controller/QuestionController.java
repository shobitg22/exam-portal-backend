package com.exam.examserver.controller;

import com.exam.examserver.entity.exam.Question;
import com.exam.examserver.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
@CrossOrigin("*")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @PostMapping
    public ResponseEntity<?> createQuestion(@RequestBody Question question){
        return ResponseEntity.ok().body(questionService.addQuestion(question));
    }
    @PutMapping
    public ResponseEntity<?> updateQuestion(@RequestBody Question updatedQuestion){
        return ResponseEntity.ok().body(questionService.updateQuestion(updatedQuestion));
    }
    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable("id") Long id){
        questionService.deleteQuestion(id);
    }

    @GetMapping
    public ResponseEntity<?> getAllQuestion(){
        return ResponseEntity.ok().body(questionService.getAllQuestions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestionById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(questionService.getQuestionById(id));
    }


    @GetMapping("/quiz/{id}")
    public ResponseEntity<?> getQuestionByQuiz(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(questionService.getQuestionsByQuiz(id));
    }
    @GetMapping("/quiz/admin/{id}")
    public ResponseEntity<?> getQuestionByQuizAdmin(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(questionService.getQuestionsByQuizForAdmin(id));
    }
    @PostMapping("/eval")
    public ResponseEntity<?> evalQuestions(@RequestBody List<Question> questionList){

    return ResponseEntity.ok(questionService.evalQuestions(questionList));
    }
}
