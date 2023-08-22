package com.project.searchone.domain.search.api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.project.searchone.domain.board.dto.BoardResponseDto;
import com.project.searchone.domain.search.dto.SearchResultResponseDto;

@RestController
@RequestMapping("/search")
public class SearchController {

    public static final String COLLECTION_NAME = "Post";

    @GetMapping
    public ResponseEntity<?> findByKeyword(@RequestParam String query,
                                      @RequestParam Integer count,
                                      @RequestParam Integer page,
                                      @RequestParam String startDate, // 0001-01-01 ~
                                      @RequestParam String endDate // ~ 9999-12-31
                                      ) throws InterruptedException, ExecutionException{
                                        
        Firestore db = FirestoreClient.getFirestore();

        List<BoardResponseDto> boards = new ArrayList<>();

        Timestamp timestampStart = Timestamp.parseTimestamp(startDate + "T00:00:00+00:00"); 
        Timestamp timestampEnd = Timestamp.parseTimestamp(endDate + "T00:00:00+00:00");

        CollectionReference  collectionReference = db.collection(COLLECTION_NAME);

        ApiFuture<QuerySnapshot> future = collectionReference
                                            .whereLessThanOrEqualTo("created_at", timestampEnd)
                                            .whereGreaterThanOrEqualTo("created_at", timestampStart)
                                            .orderBy("created_at")
                                            .get();
        
        List<QueryDocumentSnapshot> documents = future.get().getDocuments()
                                                            .stream().filter(doc-> {
                                                                String content = doc.get("content").toString();
                                                                String title = doc.get("title").toString();
                                                                 return content.contains(query) || title.contains(query) ;
                                                                } )
                                                            .collect(Collectors.toList());

        if(count.intValue() > documents.size())
            count = documents.size();      

        for (int i = 0 + page ; i < (count * page) + count.intValue() ; i++)
            boards.add(documents.get(i).toObject(BoardResponseDto.class));
        
        SearchResultResponseDto result = new SearchResultResponseDto(documents.size(),boards);
        
        return new ResponseEntity<>(result, HttpStatus.OK);

    }
    
}
