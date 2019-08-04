package io.audioshinigami.travelmantics;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import io.audioshinigami.travelmantics.activities.BaseActivity;
import io.audioshinigami.travelmantics.activities.EmailActivity;
import io.audioshinigami.travelmantics.repository.AuthRepository;

public class MainActivity extends BaseActivity
implements View.OnClickListener {

    public static String TAG =  MainActivity.class.getSimpleName();
    private Button emailSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailSignIn = findViewById(R.id.id_btn_launch_emailact);
        emailSignIn.setOnClickListener(this);

        AuthRepository.getInstance().ifUserExists("hirakoshinji@gmail.com");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.id_btn_launch_emailact:
                launchActivity(EmailActivity.class);
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
