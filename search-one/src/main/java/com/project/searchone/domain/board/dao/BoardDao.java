package com.project.searchone.domain.board.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.project.searchone.domain.board.domain.Board;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class BoardDao {
    public static final String COLLECTION_NAME = "Post";

    public List<Board> getPosts() throws ExecutionException, InterruptedException {
        // dto list
        List<Board> list = new ArrayList<>();
        // firebase conenction
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents){
            list.add(document.toObject(Board.class));
        }
        return list;
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

}
