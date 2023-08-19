package com.project.searchone.security.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.project.searchone.security.dto.myUser;

public interface UserService {

    List<myUser> getUsers() throws ExecutionException, InterruptedException;
    myUser getUserByUserName(String email) throws ExecutionException, InterruptedException;

}