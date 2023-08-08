package com.project.searchone.domain.board.dto;

import com.project.searchone.domain.board.domain.Board;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class BoardResponseDto {

    private String title;
    private String content;
    private String id;

    public BoardResponseDto(Board board) {
        this.title = board.getTitle();
        this.content = board.getContent();
        this.id = board.getId();
    }

}
