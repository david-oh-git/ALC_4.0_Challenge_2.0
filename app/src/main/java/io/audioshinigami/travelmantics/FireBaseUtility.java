package io.audioshinigami.travelmantics;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import io.audioshinigami.travelmantics.models.User;
import io.audioshinigami.travelmantics.utility.Utility;

public class FireBaseUtility {

    private static FireBaseUtility instance;

    private FireBaseUtility() {
    }

    public static FireBaseUtility getInstance(){
        if( instance == null ){
            synchronized ( FireBaseUtility.class ){
                if( instance == null ){
                    instance = new FireBaseUtility();
                } /*end IF*/
            }
        } /*end IF*/

        return instance;

    } /*end getInstance*/

    public void addUserToFireBase(User user){
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection(Utility.user_location).document(user.email)
                .set(user.toMap())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(SignInOptionsActivity.TAG, "user added successfully");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(SignInOptionsActivity.TAG, "unable to add user");
                    }
                });

    } /*end addUser*/



} /*end FirebaseUtility*/
