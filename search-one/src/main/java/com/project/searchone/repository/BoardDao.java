package com.project.searchone.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.project.searchone.dto.BoardResponseDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class BoardDao {
    public static final String COLLECTION_NAME = "Post";

    public List<BoardResponseDto> getPosts() throws ExecutionException, InterruptedException {
        // dto list
        List<BoardResponseDto> list = new ArrayList<>();
        // firebase conenction
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).get();
        System.out.print(future);
        System.out.print("fire bas connection");
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents){
            list.add(document.toObject(BoardResponseDto.class));
        }
        return list;
    }


}
