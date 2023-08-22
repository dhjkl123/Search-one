package com.project.searchone.global.config.security.application;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.project.searchone.global.config.security.dto.myUser;

public interface UserService {

    List<myUser> getUsers() throws ExecutionException, InterruptedException;
    myUser getUserByUserName(String email) throws ExecutionException, InterruptedException;

}