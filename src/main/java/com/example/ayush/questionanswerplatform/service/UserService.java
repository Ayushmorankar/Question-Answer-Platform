package com.example.ayush.questionanswerplatform.service;

import com.example.ayush.questionanswerplatform.dao.UserDao;
import com.example.ayush.questionanswerplatform.models.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class UserService {

    private final UserDao userDao;

    public void createUser(){
        User user = new User();
        userDao.save(user);
    }
}
