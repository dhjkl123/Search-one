package com.project.searchone.global.config.security.application;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.searchone.global.config.security.dto.myUser;
import com.project.searchone.global.config.security.dto.myUserPrincipal;

@Service
public class UserLoginService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        try {
            myUser myuser = userService.getUserByUserName(email);
            myUserPrincipal myuserPrincipal = new myUserPrincipal(myuser);

            return myuserPrincipal;
        } catch (ExecutionException e) {      
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;

    }
    
}
