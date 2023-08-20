package com.project.searchone.search.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.core.ApiFuture;
import com.google.cloud.Date;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

@RestController
@RequestMapping("/search")
public class KeywordSearch {

    public static final String COLLECTION_NAME = "Post";

    @GetMapping
    public List<String> findByKeyword(@RequestParam String query,
                                      @RequestParam Integer count
                                      ) throws InterruptedException, ExecutionException{
                                        
        Firestore db = FirestoreClient.getFirestore();

        List<String> result = new ArrayList<>();

        CollectionReference  collectionReference = db.collection(COLLECTION_NAME);
        ApiFuture<QuerySnapshot> future = collectionReference
                                            .whereLessThanOrEqualTo("content", query)
                                            .limit(count.intValue())
                                            .get();
        
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents){
            //list.add(document.toObject(BoardResponseDto.class));
            result.add(document.get("title").toString());
        }

        return result;

    }
    
}
