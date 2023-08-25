package com.project.searchone.global.config.security.dto;



import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@Data
@RequiredArgsConstructor
public class LoginResDto {
    private final String id;
    private final String token;
}
