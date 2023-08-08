package com.project.searchone.domain.board.dto;

import com.google.cloud.Timestamp;
import com.project.searchone.domain.board.domain.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
public class BoardPostRequestDto {

    private Integer id;
    private String user_id;
    private String content;
    private String title;
    private String obdng;
    private String svc_id;
    private String srm_id;
    private String ordr_id;
    private String req_user;
    private Boolean status;
    private Timestamp created_at;
    private Timestamp updated_at;

    public BoardPostRequestDto(Board board) {
        this.title = board.getTitle();
        this.content = board.getContent();
        this.user_id = board.getUser_id();
        this.created_at = board.getCreated_at();
        this.updated_at = board.getUpdated_at();

    }


}
