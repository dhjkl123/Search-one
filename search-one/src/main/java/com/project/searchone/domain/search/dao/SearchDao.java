package com.project.searchone.domain.search.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.project.searchone.domain.board.dto.BoardResponseDto;
import com.project.searchone.domain.search.dto.SearchResultResponseDto;

public class SearchDao {

    public final Firestore db = FirestoreClient.getFirestore();
    public final String COLLECTION_NAME = "Post";

    public SearchResultResponseDto getBoardByKeyword (String keyword, 
                                                        String startDate, 
                                                        String endDate,
                                                        Integer page) throws InterruptedException, ExecutionException{
        
        int count = 10;
        Timestamp timestampStart = Timestamp.parseTimestamp(startDate + "T00:00:00+00:00"); 
        Timestamp timestampEnd = Timestamp.parseTimestamp(endDate + "T00:00:00+00:00");
        CollectionReference  collectionReference = db.collection(COLLECTION_NAME);
        List<BoardResponseDto> boards = new ArrayList<>();   

        ApiFuture<QuerySnapshot> future = collectionReference
                                            .whereLessThanOrEqualTo("created_at", timestampEnd)
                                            .whereGreaterThanOrEqualTo("created_at", timestampStart)
                                            .orderBy("created_at")
                                            .get();
                                                   
        List<QueryDocumentSnapshot> documents = future.get().getDocuments()
                                                            .stream().filter(doc-> {
                                                                if(keyword.isEmpty())
                                                                    return true;                                               
                                                                String content = doc.get("content").toString();
                                                                String title = doc.get("title").toString();
                                                                return content.contains(keyword) || title.contains(keyword) ;
                                                                } )
                                                            .collect(Collectors.toList());
        
        if(count > documents.size())
            count = documents.size();
        
        int paging = count * (page-1);
        int lastIndex = (paging + count) > documents.size() ? documents.size() : (paging + count);

        for (int i = paging ; i < lastIndex ; i++)
        {
            BoardResponseDto post = documents.get(i).toObject(BoardResponseDto.class);
            post.setId(documents.get(i).getId());
            boards.add(post);
        }

        return new SearchResultResponseDto((documents.size() / 10) +1, boards);
                                            
    }
    
}
