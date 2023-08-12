package com.project.searchone.domain.board.domain;

import com.google.cloud.Timestamp;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Comment {
    private String id;
    private String post_id;
    private String user_id;
    private String content;
    private Timestamp created_at;
    private Timestamp updated_at;

    public void setId(String id) {
        this.id = id;
    }
}
