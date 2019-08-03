package io.audioshinigami.travelmantics;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Auth {

    public static Auth instance;
    public boolean userExists;

    private Auth(){

    }

    public void ifUserExists(final String email){

        FirebaseFirestore dbInstance =  FirebaseFirestore.getInstance();
        DocumentReference emailRef = dbInstance.collection("users").document(email);

        emailRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if( task.isSuccessful() ){
                    DocumentSnapshot doc = task.getResult();
                    getInstance().userExists = doc.exists();

                } /*end IF*/
            }
        });
    } /* end if userExists*/

    public static Auth getInstance(){
        if( instance == null ){
            synchronized ( Auth.class ){
                if( instance == null ){
                    instance = new Auth();
                } /*end IF*/
            }
        } /*end IF*/

        return instance;
    } /*end getInstance*/

} /*end Auth */
