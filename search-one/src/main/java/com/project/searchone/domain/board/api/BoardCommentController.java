package com.project.searchone.domain.board.api;

import com.project.searchone.domain.board.application.BoardCommentService;
import com.project.searchone.domain.board.dto.BoardCommentPostRequestDto;
import com.project.searchone.domain.board.dto.BoardCommentResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/board/{postId}/cmt")
public class BoardCommentController {

    private final BoardCommentService boardCommentService;

    @GetMapping("")
    public ResponseEntity<List<BoardCommentResponseDto>> getCmt(@PathVariable("postId") String postId) throws ExecutionException, InterruptedException {
        List<BoardCommentResponseDto> comments = boardCommentService.getComments(postId);

        if (comments != null) {
            return new ResponseEntity<>(comments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> postCmt(@RequestBody BoardCommentPostRequestDto req, @PathVariable String postId) throws ExecutionException, InterruptedException {
        BoardCommentResponseDto comment = boardCommentService.postComment(req);

        if (comment != null) {
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}