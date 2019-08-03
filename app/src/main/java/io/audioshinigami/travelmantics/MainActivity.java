package io.audioshinigami.travelmantics;



import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import io.audioshinigami.travelmantics.activities.BaseActivity;
import io.audioshinigami.travelmantics.repository.FireBRepository;

public class MainActivity extends BaseActivity
implements View.OnClickListener {

    public static String TAG =  MainActivity.class.getSimpleName();
    private Button emailSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailSignIn = findViewById(R.id.id_btn_email_signin);
        emailSignIn.setOnClickListener(this);

        FireBRepository.getInstance().ifUserExists("hirakoshinji@gmail.com");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.id_btn_email_signin:
                testMethod();
                break;
        } /*end switch*/
    }

    private void testMethod() {
        if( FireBRepository.getInstance().getUserExists()){
            Toast.makeText(this, "User exists", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "User does not exist", Toast.LENGTH_LONG).show();
        }

        FireBRepository.getInstance().signIn(this, "hirakoshinji@gmail.com", "shabalaka");
    }


}
