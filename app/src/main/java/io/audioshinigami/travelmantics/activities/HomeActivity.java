package io.audioshinigami.travelmantics.activities;


import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.audioshinigami.travelmantics.R;
import io.audioshinigami.travelmantics.SignInOptionsActivity;
import io.audioshinigami.travelmantics.adaptors.DealAdaptor;
import io.audioshinigami.travelmantics.repository.DealRepository;
import io.audioshinigami.travelmantics.utility.Utility;

public class HomeActivity extends AppCompatActivity
 implements View.OnClickListener {

    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseAuth fbAuth;

    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private DealAdaptor adaptor;

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
                    Utility.launchActivity(SignInOptionsActivity.class, HomeActivity.this, null);
                }else {
                    Log.v("cata", "onAuthStateChanged:signed_in:");
                }
            }
        };

        fab = findViewById(R.id.id_fab);
        fab.setOnClickListener(this);
        recyclerView = findViewById(R.id.id_recyclerview);
        setUpRecyclerView();

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
        recyclerView = findViewById(R.id.id_recyclerview);
        adaptor = new DealAdaptor();
        LinearLayoutManager LM = new LinearLayoutManager(this);
        LM.setReverseLayout(true);
        LM.setStackFromEnd(true);

        recyclerView.setLayoutManager(LM);
        recyclerView.setAdapter(adaptor);


//        DealRepository.getInstance().getAllDeals(adaptor);

    }

    @Override
    protected void onStart() {
        super.onStart();
        fbAuth.addAuthStateListener(authStateListener);

        if( fbAuth.getCurrentUser() == null ){
            Utility.launchActivity(SignInOptionsActivity.class, this, null);
        }

        DealRepository.getInstance().getAllDeals(adaptor);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        fbAuth.removeAuthStateListener(authStateListener);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.id_fab){
            Utility.launchActivity(DealActivity.class, this, null);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if( item.getItemId() == R.id.action_logout_menu){
            logOut();
        }
        return true;
    }

    private void logOut() {
        fbAuth.signOut();
    }

}
