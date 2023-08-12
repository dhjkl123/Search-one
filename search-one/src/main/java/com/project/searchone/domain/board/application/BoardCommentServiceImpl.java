package com.project.searchone.domain.board.application;

import com.project.searchone.domain.board.dao.CommentDao;
import com.project.searchone.domain.board.domain.Comment;
import com.project.searchone.domain.board.dto.BoardCommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardCommentServiceImpl implements BoardCommentService {

    private final CommentDao commentDao;

    @Override
    public List<BoardCommentResponseDto> getComments(String postId) throws ExecutionException, InterruptedException{
        List<Comment> comments = commentDao.getComments(postId);

        return comments.stream()
                .map(comment -> new BoardCommentResponseDto(comment))
                .collect(Collectors.toList());

    }
}
