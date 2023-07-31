package com.project.searchone.dto;

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

}
