package io.audioshinigami.travelmantics.repository;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import io.audioshinigami.travelmantics.Auth;


public class FireBRepository {

    private static FireBRepository instance;
    private FirebaseAuth firebaseAuth;

    private FireBRepository(){

    }

    public static FireBRepository getInstance(){
        if( instance == null ){
            synchronized ( FireBRepository.class ){
                if( instance == null ){
                    instance = new FireBRepository();
                } /*end IF*/
            }
        } /*end IF*/

        return instance;

    } /*end getInstance*/

    public void ifUserExists(final String email){
        Auth.getInstance().ifUserExists(email);
    } /* end if userExists*/

    public boolean getUserExists(){
        return Auth.getInstance().userExists;
    }

    public void signUp(AppCompatActivity context, String email, String password){
        Auth.getInstance().signUp(context, email, password);
    } /* end signUp*/

    public void signIn(AppCompatActivity context, String email, String password){
        Auth.getInstance().signIn(context, email, password);
    } /* end signIn*/
}
