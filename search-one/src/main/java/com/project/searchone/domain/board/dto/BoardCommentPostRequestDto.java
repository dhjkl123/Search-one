package com.project.searchone.domain.board.dto;

import lombok.Data;

@Data
public class BoardCommentPostRequestDto {

    private String post_id;
    private String user_id;
    private String content;

}
