package com.project.searchone.domain.board.domain;

import com.google.cloud.Timestamp;
import lombok.*;
@Data
public class Board {

    private Integer id;
    private String user_id;
    private String content;
    private String title;
    private Timestamp created_at;
    private Timestamp updated_at;


}
