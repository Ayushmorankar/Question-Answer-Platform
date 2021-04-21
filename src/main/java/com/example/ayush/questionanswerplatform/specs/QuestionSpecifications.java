package com.example.ayush.questionanswerplatform.specs;

import com.example.ayush.questionanswerplatform.models.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.SetJoin;
import java.time.LocalDate;
import java.util.List;

@Component
public class QuestionSpecifications {

    public static Specification<Question> hasTags(List<Long> tagIds){
        return (root, query, criteriaBuilder)->{
            SetJoin<Question, Tag> tagSetJoin = root.join(Question_.tags);
            return criteriaBuilder.in(tagSetJoin.get(Tag_.ID)).value(tagIds);
//            return criteriaBuilder.in(root.get(Question_.TAGS)).value(tagIds);
        };
    }

    public static Specification<Question> askedByCompanies(List<Long> companyIds){
        return (root, criteriaQuery, criteriaBuilder) ->{
            SetJoin<Question, Company> companySetJoin = root.join(Question_.companies);
            return criteriaBuilder.in(companySetJoin.get(Company_.ID)).value(companyIds);
        };
    }

    public static Specification<Question> hasTopics(List<Long> topicIds){
        return (root, criteriaQuery, criteriaBuilder) ->{
            SetJoin<Question, Topic> topicSetJoin = root.join(Question_.topics);
            return criteriaBuilder.in(topicSetJoin.get(Topic_.ID)).value(topicIds);
        };
    }

    public static Specification<Question> hasLikesMoreThan(Integer likes){
        return ((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get(Question_.NUMBER_OF_LIKES), likes));
    }

    public static Specification<Question> hasDateAfter(LocalDate date){
        return ((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get(Question_.CREATED_AT), date));
    }
}
