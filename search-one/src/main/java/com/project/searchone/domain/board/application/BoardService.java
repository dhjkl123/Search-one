package com.project.searchone.domain.board.application;

import com.project.searchone.domain.board.dto.BoardResponseDto;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface BoardService {
    List<BoardResponseDto> getPosts() throws ExecutionException, InterruptedException;

}
