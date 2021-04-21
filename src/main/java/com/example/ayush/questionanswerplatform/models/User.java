package com.example.ayush.questionanswerplatform.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@ToString(exclude = {"likedQuestions", "likedAnswers"})
@EqualsAndHashCode(exclude = {"likedQuestions", "likedAnswers"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(mappedBy = "likedBy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Question> likedQuestions = new HashSet<>();
    @ManyToMany(mappedBy = "likedBy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Answer> likedAnswers = new HashSet<>();
}
