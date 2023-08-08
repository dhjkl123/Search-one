package com.project.searchone.domain.board.domain;

import com.google.cloud.Timestamp;
import lombok.*;
@Data
@Getter
@Setter
public class Board {

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

}
