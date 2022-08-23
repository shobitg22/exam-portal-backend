package com.exam.examserver.entity.exam;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "question")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long quesId;
    //Not to send to client
    private String answer;
    private String content;
    private String image;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    //Not save in Database
    @Transient
    private String givenAnswer;

    @ManyToOne
    private Quiz quiz;

}
