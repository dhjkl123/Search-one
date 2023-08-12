package com.project.searchone.domain.board.dto;

import com.google.cloud.Timestamp;
import com.project.searchone.domain.board.domain.Comment;
import lombok.Data;

@Data
public class BoardCommentResponseDto {

    private String id;
    private String post_id;
    private String user_id;
    private String content;
    private Timestamp created_at;
    private Timestamp updated_at;

    public BoardCommentResponseDto(Comment comment){

        this.id = comment.getId();
        this.post_id = comment.getPost_id();
        this.user_id = comment.getUser_id();
        this.content = comment.getContent();
        this.created_at = comment.getCreated_at();
        this.updated_at = comment.getUpdated_at();

    }

}
