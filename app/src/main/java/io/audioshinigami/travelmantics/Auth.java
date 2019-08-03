package io.audioshinigami.travelmantics;


import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Auth {

    private static Auth instance;
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

    public void signUp(final AppCompatActivity context, String email, String password){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(context, new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if( task.isSuccessful()){
                            Toast.makeText(context,"Signup successful", Toast.LENGTH_LONG)
                                    .show();
                        } /*end IF*/
                        else {
                            Toast.makeText(context,"Oops !! something went wrong", Toast.LENGTH_LONG)
                                    .show();
                            if(task.getException().getMessage() != null ){
                                Log.d(MainActivity.TAG, "Error msg : " + task.getException().getMessage());
                            }

                        }
                    }
                });
    } /* end signUp*/

    public void signIn(final AppCompatActivity context, String email, String password){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(context, new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if( task.isSuccessful()){
                            Toast.makeText(context,"Welcome ..", Toast.LENGTH_LONG)
                                    .show();
                        } /*end IF*/
                        else {
                            Toast.makeText(context,"Oops !! something went wrong", Toast.LENGTH_LONG)
                                    .show();

                            if(task.getException().getMessage() != null ){
                                Log.d(MainActivity.TAG, "Error msg : " + task.getException().getMessage());
                            }
                        }
                    }
                });
    } /* end signIn*/

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
