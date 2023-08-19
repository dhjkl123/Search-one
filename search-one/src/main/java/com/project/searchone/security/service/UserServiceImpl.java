package com.project.searchone.security.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.project.searchone.security.dto.myUser;
import com.project.searchone.security.repository.UserDao;

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
