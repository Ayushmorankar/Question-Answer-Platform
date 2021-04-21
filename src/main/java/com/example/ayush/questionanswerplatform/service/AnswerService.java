package com.example.ayush.questionanswerplatform.service;

import com.example.ayush.questionanswerplatform.dao.AnswerDao;
import com.example.ayush.questionanswerplatform.dao.CommentDao;
import com.example.ayush.questionanswerplatform.dao.QuestionDao;
import com.example.ayush.questionanswerplatform.dao.UserDao;
import com.example.ayush.questionanswerplatform.dto.answer.AnswerLikeRequest;
import com.example.ayush.questionanswerplatform.dto.answer.AnswerRequest;
import com.example.ayush.questionanswerplatform.dto.comment.CommentRequest;
import com.example.ayush.questionanswerplatform.mapper.AnswerMapper;
import com.example.ayush.questionanswerplatform.mapper.CommentMapper;
import com.example.ayush.questionanswerplatform.models.Answer;
import com.example.ayush.questionanswerplatform.models.Comment;
import com.example.ayush.questionanswerplatform.models.Question;
import com.example.ayush.questionanswerplatform.models.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class AnswerService {

    private final AnswerDao answerDao;
    private final UserDao userDao;
    private final QuestionDao questionDao;
    private final CommentDao commentDao;
    private final AnswerMapper answerMapper;
    private final CommentMapper commentMapper;

    public Long createAnswer(AnswerRequest answerRequest) {

        User user =  userDao.findById(answerRequest.getUserId())
                .orElseThrow(RuntimeException::new);
        Question question = questionDao.findById(answerRequest.getQuestionId())
                .orElseThrow(RuntimeException::new);

        Answer answer = answerMapper.mapAnswerRequestToAnswer(answerRequest, user);

        Long answerId = answerDao.save(answer).getId();
        question.addAnswer(answer);
        return answerId;
    }

    public void likeAnswer(AnswerLikeRequest answerLikeRequest) {
        Answer answer = answerDao.findById(answerLikeRequest.getAnswerId())
                .orElseThrow(RuntimeException::new);
        User user = userDao.findById(answerLikeRequest.getUserId())
                .orElseThrow(RuntimeException::new);

        Integer likes = answer.getNumberOfLikes()+1;
        answer.setNumberOfLikes(likes);
        answer.getLikedBy().add(user);
    }

    public Long createComment(CommentRequest commentRequest){

        Answer answer = answerDao.findById(commentRequest.getAnswerId())
                .orElseThrow(RuntimeException::new);

        User user = userDao.findById(commentRequest.getUserId())
                .orElseThrow(RuntimeException::new);

        Comment comment = commentMapper.mapCommentRequestToComment(commentRequest, user);
        commentDao.save(comment);
        answer.getComments().add(comment);

        return comment.getId();
    }
}
