package com.project.searchone.security.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.google.cloud.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class myUser {

    private String email;
    private String password;
    private String name;
    private Timestamp createat;
    private Timestamp updateat;
    private boolean enabled;
    private String roles;

    private Set<String> authorities;

    public List<String> getRoleList(){
        if(this.roles.length()>0){
            return Arrays.asList(this.roles.split(","));
        }

        return new ArrayList<>();
    }

}
