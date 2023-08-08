package com.project.searchone.service;
import java.util.List;

import com.project.searchone.dto.BoardResponseDto;
import com.project.searchone.repository.BoardDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardDao boardDao;

    @Override
    public List<BoardResponseDto> getPosts() throws ExecutionException, InterruptedException {
        return boardDao.getPosts();

    }
}
