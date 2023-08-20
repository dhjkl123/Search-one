package com.project.searchone.domain.board.application;
import java.util.List;

import com.google.cloud.Timestamp;
import com.project.searchone.domain.board.domain.Board;
import com.project.searchone.domain.board.dao.BoardDao;
import com.project.searchone.domain.board.dto.BoardPostRequestDto;
import com.project.searchone.domain.board.dto.BoardPutRequestDto;
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

    @Override
    public BoardResponseDto getPost(String docId) throws  ExecutionException, InterruptedException{
        Board board = boardDao.getPost(docId);
        return new BoardResponseDto(board);
    }

    @Override
    public String postBoard(BoardPostRequestDto req) throws ExecutionException, InterruptedException {
        Board newBoard = new Board();
        newBoard.setTitle(req.getTitle());
        newBoard.setContent(req.getContent());
        newBoard.setUser_id(req.getUser_id());
        newBoard.setObdng(req.getObdng());
        newBoard.setOrdr_id(req.getOrdr_id());
        newBoard.setReq_user(req.getReq_user());
        newBoard.setSrm_id(req.getSrm_id());
        newBoard.setStatus(req.getStatus());
        newBoard.setSvc_id(req.getSvc_id());
        newBoard.setCreated_at(Timestamp.now());
        newBoard.setUpdated_at(Timestamp.now());

        return boardDao.save(newBoard);
    }

    @Override
    public String putBoard(BoardPutRequestDto req, String docId) throws ExecutionException, InterruptedException{
        Board newBoard = new Board();
        newBoard.setTitle(req.getTitle());
        newBoard.setContent(req.getContent());
        newBoard.setUpdated_at(Timestamp.now());
        return boardDao.update(newBoard, docId);
    }

    @Override
    public void deleteBoard(String docId){
        boardDao.delete(docId);
    }

}
