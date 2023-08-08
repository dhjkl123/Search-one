package com.project.searchone.service;

import com.project.searchone.dto.BoardResponseDto;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface BoardService {
    List<BoardResponseDto> getPosts() throws ExecutionException, InterruptedException;

}
