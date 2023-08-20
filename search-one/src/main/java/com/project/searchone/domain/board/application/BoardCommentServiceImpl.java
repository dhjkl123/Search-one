package com.project.searchone.domain.board.application;

import com.google.cloud.Timestamp;
import com.project.searchone.domain.board.dao.CommentDao;
import com.project.searchone.domain.board.domain.Comment;
import com.project.searchone.domain.board.dto.BoardCommentPostRequestDto;
import com.project.searchone.domain.board.dto.BoardCommentPutRequestDto;
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
    public BoardCommentResponseDto postComment(BoardCommentPostRequestDto req, String postId) throws ExecutionException, InterruptedException {

        // Comment domain에서 Use @Builder annotation
        Comment newComment = Comment.builder()
                .post_id(postId)
                .user_id(req.getUser_id())
                .content(req.getContent())
                .created_at(Timestamp.now())
                .updated_at(Timestamp.now())
                .build();

        return commentDao.save(newComment);

    }

    @Override
    public BoardCommentResponseDto updateComment(BoardCommentPutRequestDto req, String cmtId) throws ExecutionException, InterruptedException {

        Comment updateComment = Comment.builder()
                .content(req.getContent())
                .updated_at(Timestamp.now())
                .build();

        return commentDao.update(updateComment, cmtId);
    }

    @Override
    public String deleteComment(String cmtId) {
        commentDao.delete(cmtId);
        return cmtId;
    }
}
