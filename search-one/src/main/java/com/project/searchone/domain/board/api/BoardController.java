package com.project.searchone.domain.board.api;

import com.project.searchone.domain.board.application.BoardService;
import com.project.searchone.domain.board.dto.BoardPostRequestDto;
import com.project.searchone.domain.board.dto.BoardResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("")
    public ResponseEntity<?> getBoard() throws ExecutionException, InterruptedException{
        List<BoardResponseDto> boards = boardService.getPosts();

        if (boards != null) {
            return new ResponseEntity<>(boards, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> postBoard(@RequestBody BoardPostRequestDto boardReq) throws ExecutionException, InterruptedException {
        String docId = boardService.postBoard(boardReq);
        return new ResponseEntity<>(docId, HttpStatus.OK);
    }
}
