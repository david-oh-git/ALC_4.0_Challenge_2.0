package io.audioshinigami.travelmantics.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import io.audioshinigami.travelmantics.R;

public class EmailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        if( getSupportActionBar() != null )
            getSupportActionBar().setTitle(getResources().getString(R.string.str_email_activity_title));
    }
}
