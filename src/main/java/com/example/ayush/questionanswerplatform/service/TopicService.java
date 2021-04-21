package com.example.ayush.questionanswerplatform.service;

import com.example.ayush.questionanswerplatform.dao.TopicDao;
import com.example.ayush.questionanswerplatform.dto.topic.SubTopicRequest;
import com.example.ayush.questionanswerplatform.dto.topic.TopicRequest;
import com.example.ayush.questionanswerplatform.models.Question;
import com.example.ayush.questionanswerplatform.models.Topic;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@AllArgsConstructor
@Transactional
public class TopicService {

    private final TopicDao topicDao;

    public void createTopic(TopicRequest topicRequest){
        Topic topic = new Topic();
        topic.setName(topicRequest.getName());
        topicDao.save(topic);
    }

    public void createSubTopic(SubTopicRequest subTopicRequest) {

        Topic topic = topicDao.findById(subTopicRequest.getTopicId())
                .orElseThrow(RuntimeException::new);

        Topic subTopic = new Topic();
        subTopic.setName(subTopicRequest.getSubTopicName());
        subTopic.setTopic(topic);
        topicDao.save(subTopic);

        //Adding reference(self-referencing) of recently created topic as given topic's subtopic
        topic.getSubTopics().add(subTopic);
    }

    public void deleteTopic(Long id) {

        Topic topic = topicDao.findById(id)
                .orElseThrow(RuntimeException::new);

        Set<Question> topicSet = topic.getQuestions();

        //Removing reference of topic from question
        for(Question question: topicSet){
            question.removeTopic(topic);
        }

        topicDao.delete(topic);
    }

    public void deleteSubtopic(Long subtopicId) {

        Topic subtopic = topicDao.findById(subtopicId)
                .orElseThrow(RuntimeException::new);

        Set<Question> questionsSet = subtopic.getQuestions();

        //Removing reference of topic from question
        for(Question question: questionsSet){
            question.removeTopic(subtopic);
        }

        topicDao.delete(subtopic);
    }

    public void updateTopic(Long id, String updatedName) {

        Topic topic = topicDao.findById(id)
                .orElseThrow(RuntimeException::new);

        topic.setName(updatedName);
    }
}
