package com.project.searchone.domain.board.dto;

import com.google.cloud.Timestamp;
import lombok.Data;

@Data
public class BoardCommentPutRequestDto {

    private String content;
    private Timestamp updated_at;

}
