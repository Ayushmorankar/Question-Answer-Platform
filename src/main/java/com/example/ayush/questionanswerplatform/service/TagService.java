package com.example.ayush.questionanswerplatform.service;

import com.example.ayush.questionanswerplatform.dao.TagDao;
import com.example.ayush.questionanswerplatform.dto.tag.TagRequest;
import com.example.ayush.questionanswerplatform.models.Question;
import com.example.ayush.questionanswerplatform.models.Tag;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@AllArgsConstructor
@Transactional
public class TagService {

    private final TagDao tagDao;

    public void createTag(TagRequest tagRequest){
        Tag tag = new Tag();
        tag.setName(tagRequest.getName());
        tagDao.save(tag);
    }

    public void deleteTag(Long id) {

        Tag tag = tagDao.findById(id)
                .orElseThrow(RuntimeException::new);

        Set<Question> questionSet = tag.getQuestions();

        for(Question question: questionSet){
            question.removeTag(tag);
        }

        tagDao.delete(tag);
    }

    public void updateTag(Long id, String updatedName) {

        Tag tag = tagDao.findById(id)
                .orElseThrow(RuntimeException::new);
        tag.setName(updatedName);

    }
}
