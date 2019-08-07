package io.audioshinigami.travelmantics;



import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.audioshinigami.travelmantics.activities.EmailSignInActivity;
import io.audioshinigami.travelmantics.activities.googleauth.GoogleSignInActivity;
import io.audioshinigami.travelmantics.repository.AuthRepository;

public class SignInOptionsActivity extends AppCompatActivity
implements View.OnClickListener {

    public static String TAG =  SignInOptionsActivity.class.getSimpleName();
    private Button emailSignIn;

    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseAuth fbAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if( getSupportActionBar() != null ){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        emailSignIn = findViewById(R.id.id_btn_launch_emailact);
        emailSignIn.setOnClickListener(this);

        findViewById(R.id.id_btn_launch_googleact).setOnClickListener(this);

        AuthRepository.getInstance().ifUserExists("hirakoshinji@gmail.com");

        fbAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = fbAuth.getCurrentUser();
                if( user != null ){
                    finish();
                }
            }
        };
    }

    @Override
    protected void onPause(){
        super.onPause();

        fbAuth.removeAuthStateListener(authStateListener);
    }

    @Override
    protected void onStart(){
        super.onStart();
        fbAuth.addAuthStateListener(authStateListener);

    }

    @Override
    protected void onResume() {
        super.onResume();

        FirebaseUser user = fbAuth.getCurrentUser();
        if( user != null ){
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if( item.getItemId() == android.R.id.home){
            finish();
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.id_btn_launch_emailact:
                launchActivity(EmailSignInActivity.class);
                break;

            case R.id.id_btn_launch_googleact:
                launchActivity(GoogleSignInActivity.class);
                finish();
                break;
        } /*end switch*/
    }

    private void testMethod() {
        if( AuthRepository.getInstance().getUserExists()){
            Toast.makeText(this, "User exists", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "User does not exist", Toast.LENGTH_LONG).show();
        }

        AuthRepository.getInstance().signIn(this, "hirakoshinji@gmail.com", "shabalaka");
    }

    private  <T extends AppCompatActivity> void launchActivity(Class<T> inputClass){
        Intent intent = new Intent(this, inputClass);
        startActivity(intent);
    }


}
