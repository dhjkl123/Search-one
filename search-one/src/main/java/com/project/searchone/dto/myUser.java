package com.project.searchone.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.cloud.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
public class myUser {

    private String email;
    private String password;
    private String name;
    private Timestamp createat;
    private Timestamp updateat;
    private boolean enabled;
    private String roles;

    public List<String> getRoleList(){
        if(this.roles.length()>0){
            return Arrays.asList(this.roles.split(","));
        }

        return new ArrayList<>();
    }

}
