package com.project.searchone.domain.board.application;

import com.project.searchone.domain.board.dto.BoardPostRequestDto;
import com.project.searchone.domain.board.dto.BoardResponseDto;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface BoardService {
    List<BoardResponseDto> getPosts() throws ExecutionException, InterruptedException;
    String postBoard(BoardPostRequestDto req) throws ExecutionException, InterruptedException;
}
