package com.exam.examserver.controller;

import com.exam.examserver.entity.exam.Quiz;
import com.exam.examserver.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @PostMapping
    public ResponseEntity<?> createQuiz(@RequestBody Quiz quiz){
        return ResponseEntity.ok().body(quizService.addQuiz(quiz));
    }
    @PutMapping
    public ResponseEntity<?> updateQuiz(@RequestBody Quiz updatedQuiz){
        return ResponseEntity.ok().body(quizService.updateQuiz(updatedQuiz));
    }
    @DeleteMapping("/{id}")
    public void deleteQuiz(@PathVariable("id") Long id){
        quizService.deleteQuiz(id);
    }

    @GetMapping
    public ResponseEntity<?> getAllQuiz(){
        return ResponseEntity.ok().body(quizService.getAllQuiz());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuizById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(quizService.getQuizById(id));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> getQuizByCategory(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(quizService.getQuizByCategory(id));
    }
    @GetMapping("/by/{name}")
    public ResponseEntity<?> getQuizByName(@PathVariable("name") String name){
        return ResponseEntity.ok().body(quizService.findByName(name));
    }
}
