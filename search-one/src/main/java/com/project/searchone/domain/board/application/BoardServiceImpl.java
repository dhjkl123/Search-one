package com.project.searchone.domain.board.application;
import java.util.List;

import com.project.searchone.domain.board.domain.Board;
import com.project.searchone.domain.board.dao.BoardDao;
import com.project.searchone.domain.board.dto.BoardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardDao boardDao;

    @Override
    public List<BoardResponseDto> getPosts() throws ExecutionException, InterruptedException {
        List<Board> boards = boardDao.getPosts();

        // Board 객체들을 BoardResponseDto로 변환하여 리스트로 반환
        return boards.stream()
                .map(board -> new BoardResponseDto(board))
                .collect(Collectors.toList());
    }
}
