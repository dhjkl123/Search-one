package com.project.searchone.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.project.searchone.dto.myUser;
import com.project.searchone.repository.UserDao;

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