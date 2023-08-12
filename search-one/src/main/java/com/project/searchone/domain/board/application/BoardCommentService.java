package com.project.searchone.domain.board.application;

import com.project.searchone.domain.board.dto.BoardCommentPostRequestDto;
import com.project.searchone.domain.board.dto.BoardCommentPutRequestDto;
import com.project.searchone.domain.board.dto.BoardCommentResponseDto;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface BoardCommentService {
    List<BoardCommentResponseDto> getComments(String postId) throws ExecutionException, InterruptedException;

    BoardCommentResponseDto postComment(BoardCommentPostRequestDto req) throws ExecutionException, InterruptedException;

    BoardCommentResponseDto updateComment(BoardCommentPutRequestDto req, String cmtId) throws  ExecutionException, InterruptedException;

}
