package com.project.searchone.domain.search.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.project.searchone.domain.board.dto.BoardResponseDto;

@RestController
@RequestMapping("/search")
public class SearchController {

    public static final String COLLECTION_NAME = "Post";

    @GetMapping
    public ResponseEntity<?> findByKeyword(@RequestParam String query,
                                      @RequestParam Integer count
                                      ) throws InterruptedException, ExecutionException{
                                        
        Firestore db = FirestoreClient.getFirestore();

        List<BoardResponseDto> boards = new ArrayList<>();

        CollectionReference  collectionReference = db.collection(COLLECTION_NAME);
        ApiFuture<QuerySnapshot> future = collectionReference
                                            .whereGreaterThanOrEqualTo("content", query)
                                            .limit(count.intValue())
                                            .get();
        
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents){
            boards.add(document.toObject(BoardResponseDto.class));

        }

        return new ResponseEntity<>(boards, HttpStatus.OK);

    }
    
}
