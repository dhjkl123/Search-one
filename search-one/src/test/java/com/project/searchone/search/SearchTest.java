package com.project.searchone.search;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.Date;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

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
    
        Date dateStart = Date.parseDate("0001-01-01");
        Date dateEnd = Date.parseDate("9999-12-31");

        ApiFuture<QuerySnapshot> future = collectionReference
                                            .whereLessThanOrEqualTo("content", "발생")
                                            //.startAfter(lastVisible)
                                      
                                           

                                            .limit(1)
                                            .get();
    
    List<QueryDocumentSnapshot> documents = future.get().getDocuments();
    for (QueryDocumentSnapshot document : documents){
        //list.add(document.toObject(BoardResponseDto.class));
        result.add(document.get("title").toString());
    }

    lastVisible = documents.get(documents.size()-1);




    }
    
}
