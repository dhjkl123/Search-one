package com.project.searchone.domain.board.dto;

import com.google.cloud.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardPutRequestDto {

    private String content;
    private String title;
    private Timestamp updated_at;

}
