package com.project.searchone.dto;

import com.google.cloud.Timestamp;
import lombok.*;

@NoArgsConstructor
@Data
@ToString
public class BoardResponseDto {

    private Integer id;
    private String user_id;
    private String content;
    private String title;
    private Timestamp created_at;
    private Timestamp updated_at;

}
