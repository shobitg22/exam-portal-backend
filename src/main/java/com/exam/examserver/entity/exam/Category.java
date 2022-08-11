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
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cId;
    private String title;
    private String description;
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Quiz> quizSet = new HashSet<>();
}
