package com.project.searchone.global.config.security.application;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.project.searchone.global.config.security.dao.UserDao;
import com.project.searchone.global.config.security.dto.myUser;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    public final UserDao userDao;

    @Override
    public List<myUser> getUsers() throws ExecutionException, InterruptedException {
        return userDao.getUsers();
    }

    @Override
    public myUser getUserByUserName(String email) throws ExecutionException, InterruptedException {
        return userDao.getUserByUserName(email);
    }
    
}
