package com.example.ayush.questionanswerplatform.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@ToString(exclude = {"subTopics", "questions"})
@EqualsAndHashCode(exclude = {"subTopics", "questions"})
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "topic")
    private Set<Topic> subTopics = new HashSet<>();
    @ManyToOne(fetch = FetchType.LAZY)
    private Topic topic;
    @ManyToMany(mappedBy = "topics", fetch = FetchType.LAZY)
    private Set<Question> questions = new HashSet<>();

}