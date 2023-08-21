package com.project.searchone.search;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.assertj.core.api.filter.FilterOperator;
import org.hibernate.sql.ast.tree.predicate.FilterPredicate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firestore.v1.StructuredQuery.Filter;

@SpringBootTest
public class SearchTest {

    public static final String COLLECTION_NAME = "Post";

    public DocumentSnapshot lastVisible;

    @Test
    void findByKeyword() throws InterruptedException, ExecutionException{

    List<String> result = new ArrayList<>();

    try{
        FileInputStream serviceAccount = 
            new FileInputStream("../search-one/src/main/resources/serviceAccountKey.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();
        FirebaseApp.initializeApp(options);
    }catch (Exception e){
        e.printStackTrace();
    }

    Firestore db = FirestoreClient.getFirestore();


    CollectionReference  collectionReference = db.collection(COLLECTION_NAME);



    Timestamp timestampStart = Timestamp.parseTimestamp("2023-08-13T00:00:00+00:00");
    Timestamp timestampEnd = Timestamp.parseTimestamp("2023-08-20T00:00:00+00:00");

    ApiFuture<QuerySnapshot> future = collectionReference
                                        //.whereLessThanOrEqualTo("content", "발생")
                                        .whereLessThanOrEqualTo("created_at", timestampEnd)
                                        .whereGreaterThanOrEqualTo("created_at", timestampStart)
                                        .orderBy("created_at")
                                        //.startAt(timestampStart)
                                        
                                        //.limit(3)
                                        .get();
    
    List<QueryDocumentSnapshot> documents = future.get().getDocuments()
                                            .stream().filter(doc-> doc.get("content").toString().contains("발생"))
                                            .collect(Collectors.toList());

    
    for (QueryDocumentSnapshot document : documents){
        //list.add(document.toObject(BoardResponseDto.class));
        result.add(document.get("title").toString());
    }

    //lastVisible = documents.get(documents.size()-1);




    }
    
}
