package com.example.ayush.questionanswerplatform.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@ToString(exclude = {"answers", "companies", "topics", "likedBy", "tags"})
@EqualsAndHashCode(exclude = {"answers", "companies", "topics", "likedBy", "tags"})
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "question_id")
    private Set<Answer> answers = new HashSet<>();
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "question_company_mapping",
    joinColumns = {@JoinColumn(name = "question_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "company_id", referencedColumnName = "id")})
    private Set<Company> companies = new HashSet<>();
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "question_topics",
            joinColumns = {@JoinColumn(name = "question_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "topic_id", referencedColumnName = "id")})
    private Set<Topic> topics = new HashSet<>();
    @Lob
    private String text;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "question_likes",
            joinColumns = {@JoinColumn(name = "question_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
    private Set<User> likedBy = new HashSet<>();
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "question_tags",
            joinColumns = {@JoinColumn(name = "question_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "id")})
    private Set<Tag> tags = new HashSet<>();
    private Integer numberOfLikes;
    private LocalDate createdAt;

    //utility methods
    public void addAnswer(Answer answer){
        this.answers.add(answer);
    }

    public void removeTag(Tag tag){
        this.tags.remove(tag);
    }

    public void removeTopic(Topic topic){
        this.topics.remove(topic);
    }

    public void removeCompany(Company company){
        this.companies.remove(company);
    }
}
