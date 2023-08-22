package com.project.searchone.domain.board.dto;

import com.google.cloud.Timestamp;
import com.project.searchone.domain.board.domain.Board;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class BoardResponseDto {

    private String id;
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

    public BoardResponseDto(Board board) {

        this.id = board.getId();
        this.user_id = board.getUser_id();
        this.content = board.getContent();
        this.title = board.getTitle();
        this.obdng = board.getObdng();
        this.svc_id = board.getSvc_id();
        this.srm_id = board.getSrm_id();
        this.ordr_id = board.getOrdr_id();
        this.req_user = board.getReq_user();
        this.status = board.getStatus();
        this.created_at = board.getCreated_at();
        this.updated_at = board.getUpdated_at();

    }

}
