package com.project.searchone.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.project.searchone.dto.myUser;

public interface UserService {

    List<myUser> getUsers() throws ExecutionException, InterruptedException;

}