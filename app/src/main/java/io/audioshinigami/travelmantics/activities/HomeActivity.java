package io.audioshinigami.travelmantics.activities;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.audioshinigami.travelmantics.MainActivity;
import io.audioshinigami.travelmantics.R;

public class HomeActivity extends BaseActivity {

    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseAuth fbAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if( getSupportActionBar() != null){
            getSupportActionBar().setTitle("Travelmantics");
        } /*end IF*/

        fbAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = fbAuth.getCurrentUser();
                if( user == null ){
                    launchActivity(MainActivity.class);
                }else {
                    Log.v("TAG", "onAuthStateChanged:signed_in:");
                    Toast.makeText(HomeActivity.this, "User is logged in", Toast.LENGTH_SHORT).show();
                }
            }
        };
    } /*end onCreate*/

    @Override
    protected void onStart() {
        super.onStart();
        fbAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        fbAuth.removeAuthStateListener(authStateListener);
    }

    private  <T extends AppCompatActivity> void launchActivity(Class<T> inputClass){
        Intent intent = new Intent(this, inputClass);
        startActivity(intent);
    }
}
