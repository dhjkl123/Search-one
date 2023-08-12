package com.project.searchone.domain.board.application;

import com.google.cloud.Timestamp;
import com.project.searchone.domain.board.dao.CommentDao;
import com.project.searchone.domain.board.domain.Comment;
import com.project.searchone.domain.board.dto.BoardCommentPostRequestDto;
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

    @Override
    public BoardCommentResponseDto postComment(BoardCommentPostRequestDto req) throws ExecutionException, InterruptedException {

        // 기존 set pattern
//        Comment newComment = new Comment();
//        newComment.setPost_id(req.getPost_id());
//        newComment.setUser_id(req.getUser_id());
//        newComment.setContent(req.getContent());
//        newComment.setCreated_at(req.getCreated_at());
//        newComment.setUpdated_at(req.getUpdated_at());

        // Comment domain에서 Use @Builder annotation
        Comment newComment = Comment.builder()
                .post_id(req.getPost_id())
                .user_id(req.getUser_id())
                .content(req.getContent())
                .created_at(Timestamp.now())
                .updated_at(Timestamp.now())
                .build();

        return commentDao.save(newComment);

    }
}
