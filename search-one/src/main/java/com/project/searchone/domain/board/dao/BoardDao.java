package com.project.searchone.domain.board.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.project.searchone.domain.board.domain.Board;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Repository
public class BoardDao {
    public static final String COLLECTION_NAME = "Post";

    public List<Board> getPosts() throws ExecutionException, InterruptedException {
        List<Board> list = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents){
            Board board = document.toObject(Board.class);
            String docId = document.getId();
            board.setId(docId);
            list.add(board);
        }
        return list;
    }

    public Board getPost(String docId) throws ExecutionException, InterruptedException{
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection(COLLECTION_NAME).document(docId);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();

        if (document.exists()) {
            Board board = document.toObject(Board.class);
            board.setId(docId);
            return board;
        } else {
            return null;
        }
    }

//    public List<Board> getPost() throws ExecutionException, InterruptedException {
//        List<Board> list = new ArrayList<>();
//        Firestore db = FirestoreClient.getFirestore();
//        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).get();
//    }

    public String save(Board board) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection(COLLECTION_NAME).add(board).get();
        String documentId = docRef.getId();
        return documentId;
    }

    public String update(Board board, String docId) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection(COLLECTION_NAME).document(docId);

        Map<String, Object> updates = new HashMap<>();
        updates.put("title", board.getTitle());
        updates.put("content", board.getContent());

        // writeResult를 통해서 추가 작업 가능
        ApiFuture<WriteResult> writeResult = docRef.update(updates);

        return docId;
    }

    public void delete(String docId) {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection(COLLECTION_NAME).document(docId);
        docRef.delete();
    }

}
