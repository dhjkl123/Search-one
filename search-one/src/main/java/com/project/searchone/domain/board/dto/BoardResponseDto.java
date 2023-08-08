package com.project.searchone.domain.board.dto;

import com.project.searchone.domain.board.domain.Board;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class BoardResponseDto {

    private Integer id;
    private String title;
    private String content;

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
    }

}
