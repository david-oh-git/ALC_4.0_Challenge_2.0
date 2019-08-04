package io.audioshinigami.travelmantics.repository;

import androidx.appcompat.app.AppCompatActivity;


import io.audioshinigami.travelmantics.Auth;


public class AuthRepository {

    private static AuthRepository instance;

    private AuthRepository(){

    }

    public static AuthRepository getInstance(){
        if( instance == null ){
            synchronized ( AuthRepository.class ){
                if( instance == null ){
                    instance = new AuthRepository();
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
