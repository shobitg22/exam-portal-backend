package com.exam.examserver.entity.exam;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "quiz")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long qId;
    private String title;
    private String description;
    private String maxMarks;
    private String numberOfQuestions;
    private Boolean isActive=false;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
    @OneToMany(mappedBy = "quiz",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Question> questionSet = new HashSet<>();

}
