package io.audioshinigami.travelmantics.activities;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.audioshinigami.travelmantics.SignInOptionsActivity;
import io.audioshinigami.travelmantics.R;
import io.audioshinigami.travelmantics.repository.DealRepository;

public class HomeActivity extends AppCompatActivity
 implements View.OnClickListener {

    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseAuth fbAuth;

    private FloatingActionButton fab;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        fbAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = fbAuth.getCurrentUser();
                if( user == null ){
                    launchActivity(SignInOptionsActivity.class);
                }else {
                    Log.v("TAG", "onAuthStateChanged:signed_in:");
                    Toast.makeText(HomeActivity.this, "User is logged in", Toast.LENGTH_SHORT).show();
                    setUpRecyclerView();
                }
            }
        };

        fab = findViewById(R.id.id_fab);
        fab.setOnClickListener(this);
        recyclerView = findViewById(R.id.id_recyclerview);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && fab.getVisibility() == View.VISIBLE) {
                    fab.hide();
                } else if (dy < 0 && fab.getVisibility() != View.VISIBLE) {
                    fab.show();
                }
            }
        });
    } /*end onCreate*/

    private void setUpRecyclerView() {
    }

    @Override
    protected void onStart() {
        super.onStart();
        fbAuth.addAuthStateListener(authStateListener);
        DealRepository.getInstance().getAllDeals();
    }

    @Override
    protected void onPause() {
        super.onPause();
        fbAuth.removeAuthStateListener(authStateListener);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.id_fab){
            launchActivity(DealActivity.class);
        }
    }

    private  <T extends AppCompatActivity> void launchActivity(Class<T> inputClass){
        Intent intent = new Intent(this, inputClass);
        startActivity(intent);
    }
}
