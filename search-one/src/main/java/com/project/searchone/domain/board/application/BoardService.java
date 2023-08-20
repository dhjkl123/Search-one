package com.project.searchone.domain.board.application;

import com.project.searchone.domain.board.dto.BoardPostRequestDto;
import com.project.searchone.domain.board.dto.BoardPutRequestDto;
import com.project.searchone.domain.board.dto.BoardResponseDto;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface BoardService {
    List<BoardResponseDto> getPosts() throws ExecutionException, InterruptedException;
    BoardResponseDto getPost(String docId) throws  ExecutionException, InterruptedException;
    String postBoard(BoardPostRequestDto req) throws ExecutionException, InterruptedException;

    String putBoard(BoardPutRequestDto req, String docId) throws ExecutionException, InterruptedException;

    void deleteBoard(String docId);
}
