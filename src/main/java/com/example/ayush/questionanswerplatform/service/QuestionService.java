package com.example.ayush.questionanswerplatform.service;

import com.example.ayush.questionanswerplatform.dao.*;
import com.example.ayush.questionanswerplatform.dto.answer.AnswerResponse;
import com.example.ayush.questionanswerplatform.dto.answer.FilteredQuestionAnswerResponse;
import com.example.ayush.questionanswerplatform.dto.comment.CommentResponse;
import com.example.ayush.questionanswerplatform.dto.question.FilteredQuestionResponse;
import com.example.ayush.questionanswerplatform.dto.question.QuestionLikeRequest;
import com.example.ayush.questionanswerplatform.dto.question.QuestionRequest;
import com.example.ayush.questionanswerplatform.dto.question.QuestionResponse;
import com.example.ayush.questionanswerplatform.mapper.AnswerMapper;
import com.example.ayush.questionanswerplatform.mapper.CommentMapper;
import com.example.ayush.questionanswerplatform.mapper.QuestionMapper;
import com.example.ayush.questionanswerplatform.models.*;
import com.example.ayush.questionanswerplatform.specs.QuestionSpecifications;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class QuestionService {

    private final QuestionDao questionDao;
    private final TopicDao topicDao;
    private final CompanyDao companyDao;
    private final TagDao tagDao;
    private final UserDao userDao;

    private final QuestionMapper questionMapper;
    private final AnswerMapper answerMapper;
    private final CommentMapper commentMapper;

    public Long createQuestion(QuestionRequest questionRequest){

        //To check if userId is present in database
        User user = userDao.findById(questionRequest.getUserId())
                .orElseThrow(RuntimeException::new);

        Set<Company> companies = new HashSet<>();
        Set<Tag> tags = new HashSet<>();
        Set<Topic> topics = new HashSet<>();

        //Collecting entity objects from their ids

        questionRequest.getSubTopicIds().forEach((subTopicId)->{
            Topic topic = topicDao.findById(subTopicId)
                    .orElseThrow(RuntimeException::new);
            topics.add(topic);
        });

        if(questionRequest.getCompanyIds()!=null) {
            questionRequest.getCompanyIds().forEach((id) -> {
                Company company = companyDao.findById(id)
                        .orElseThrow(RuntimeException::new);
                companies.add(company);
            });
        }

        if(questionRequest.getTagIds()!=null) {
            questionRequest.getTagIds().forEach((tagId) -> {
                Tag tag = tagDao.findById(tagId)
                        .orElseThrow(RuntimeException::new);
                tags.add(tag);
            });
        }

        Question question = questionMapper.mapToQuestion(questionRequest, topics, companies, tags);
        return questionDao.save(question).getId();
    }

    public Set<FilteredQuestionResponse> filterQuestions(Optional<List<Long>> tagIds,
                                                         Optional<List<Long>> topicIds,
                                                         Optional<List<Long>> companyIds,
                                                         Optional<Integer> likes,
                                                         Optional<String> afterDate){


        //Creating empty specification and chaining specifications if given field is present
        Specification<Question> specification = Specification.where(null);

        List<Long> extractedTagIds;
        List<Long> extractedCompanyIds;

        if(tagIds.isPresent()){
            specification = specification.and(QuestionSpecifications.hasTags(tagIds.get()));
        }
        if(topicIds.isPresent()){
            specification = specification.and(QuestionSpecifications.hasTopics(topicIds.get()));
        }
        if(companyIds.isPresent()){
            specification = specification.and(QuestionSpecifications.askedByCompanies(companyIds.get()));
        }
        if(likes.isPresent()){
            Integer numberOfLikes = likes.get();
            specification = specification.and(QuestionSpecifications.hasLikesMoreThan(numberOfLikes));
        }
        if(afterDate.isPresent()){
            LocalDate date = LocalDate.parse(afterDate.get());
            specification = specification.and(QuestionSpecifications.hasDateAfter(date));
        }

        //Finding list of questions for given constraints
        List<Question> filteredQuestions = questionDao.findAll(specification);

        Set<FilteredQuestionResponse> response = new HashSet<>();

        for(Question question: filteredQuestions){

            FilteredQuestionAnswerResponse mostLikedAnswer = null;

            //find the answer to the question with most likes
            int maxLikes = -1;
            for(Answer answer: question.getAnswers()){
                Integer numberOfLikes = answer.getNumberOfLikes();
                if(maxLikes<numberOfLikes){
                    mostLikedAnswer = answerMapper.mapAnswerToFilteredQuestionAnswerResponse(answer);
                    maxLikes = numberOfLikes;
                }
            }

            extractedTagIds = question.getTags().stream()
                    .map(Tag::getId)
                    .collect(Collectors.toList());

            extractedCompanyIds = question.getCompanies().stream()
                    .map(Company::getId)
                    .collect(Collectors.toList());


            //Putting retrieved data into response object
            response.add(questionMapper.mapToFilteredQuestionResponse(question, mostLikedAnswer,
                    extractedTagIds, extractedCompanyIds));
        }

        return response;
    }

    public QuestionResponse findQuestionById(Long id){

        Question question = questionDao.findById(id).
                orElseThrow(RuntimeException::new);

        List<AnswerResponse> answerResponses = new ArrayList<>();

        for(Answer answer: question.getAnswers()){
            List<CommentResponse> comments = answer.getComments().stream()
                    .map(commentMapper::mapToCommentResponse)
                    .collect(Collectors.toList());
            answerResponses.add(answerMapper.mapToAnswerResponse(answer, comments));
        }

        Set<Topic> subTopicIds = question.getTopics();

        Set<Long> topicIds = subTopicIds.stream()
                .map(topic->topic.getTopic().getId())
                .collect(Collectors.toSet());

        List<Long> tagIds = question.getTags().stream()
                .map(Tag::getId)
                .collect(Collectors.toList());

        List<Long> companyIds = question.getCompanies().stream()
                .map(Company::getId)
                .collect(Collectors.toList());

        return questionMapper.mapToQuestionResponse(question, answerResponses, topicIds, tagIds, companyIds);
    }


    public void likeQuestion(QuestionLikeRequest questionLikeRequest) {

        Question question = questionDao.findById(questionLikeRequest.getQuestionId())
                .orElseThrow(RuntimeException::new);
        User user = userDao.findById(questionLikeRequest.getUserId())
                .orElseThrow(RuntimeException::new);

        Integer likes = question.getNumberOfLikes()+1;
        question.setNumberOfLikes(likes);

        question.getLikedBy().add(user);
    }
}
