package com.project.searchone.domain.board.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.project.searchone.domain.board.application.BoardCommentService;
import com.project.searchone.domain.board.domain.Board;
import com.project.searchone.domain.board.domain.Comment;
import com.project.searchone.domain.board.dto.BoardCommentResponseDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class CommentDao {

    public static final String COLLECTION_NAME = "Comment";

    public List<Comment> getComments(String postId) throws ExecutionException, InterruptedException {

        // firebase에서 Collection 가져오기
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference commentsRef = db.collection(COLLECTION_NAME);

        // postId를 통해서 조건에 맞는 QuerySnapShot(비동기), 문서를 가져옴
        Query query = commentsRef.whereEqualTo("post_id", postId);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();

        // QueryDocumentSnapshot 객체를 Comment 객체로 변환
        // matchingComments에 해당 Comment 추가해서 리턴
        List<Comment> matchingComments = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            Comment comment = document.toObject(Comment.class);
            matchingComments.add(comment);
        }

        return  matchingComments;

    }
}
