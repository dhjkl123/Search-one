package com.project.searchone.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.project.searchone.dto.myUser;

@Repository
public class UserDao {

    public static final String COLLECTION_NAME = "User";

    public List<myUser> getUsers() throws ExecutionException, InterruptedException {
        List<myUser> list = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            list.add(document.toObject(myUser.class));
        }
        return list;
    }

    public myUser getUserByUserName(String email) throws ExecutionException, InterruptedException {


        myUser user = new myUser();
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            if(document.toObject(myUser.class).getEmail().equals(email)){
                user = document.toObject(myUser.class);

            }
        }
        
        return user;
    }
}
