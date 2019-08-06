package io.audioshinigami.travelmantics.activities;


import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import io.audioshinigami.travelmantics.R;
import io.audioshinigami.travelmantics.repository.AuthRepository;

public class EmailSignInActivity extends AppCompatActivity
 implements View.OnClickListener {

    private Button signUp;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if( getSupportActionBar() != null ){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if( getSupportActionBar() != null )
            getSupportActionBar().setTitle(getResources().getString(R.string.str_email_activity_title));

        signUp = findViewById(R.id.id_btn_email_signup);
        signUp.setOnClickListener(this);

        login = findViewById(R.id.id_btn_email_login);
        login.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if( view.getId() == R.id.id_btn_email_signup){
            createAccount();
        } /*end IF*/
        else if( view.getId() == R.id.id_btn_email_login){
            login();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if( item.getItemId() == android.R.id.home){
            finish();
        }
        return true;
    }

    private void login() {
        TextInputEditText emailEdit = findViewById(R.id.id_edit_email);
        TextInputEditText passwordEdit = findViewById(R.id.id_editxt_password);

        if(!isFormValid(emailEdit, passwordEdit)){
            return;
        }

        String email = emailEdit.getText().toString();
        String password = passwordEdit.getText().toString();


        AuthRepository.getInstance().ifUserExists(emailEdit.getText().toString());

        AuthRepository.getInstance().signIn(this,email,password);
    } /*end login*/

    private void createAccount(){
        TextInputEditText emailEdit = findViewById(R.id.id_edit_email_layout);
        TextInputEditText passwordEdit = findViewById(R.id.id_edit_password_layout);

        if(!isFormValid(emailEdit, passwordEdit)){
            return;
        }

        String email = emailEdit.getText().toString();
        String password = passwordEdit.getText().toString();

        AuthRepository.getInstance().ifUserExists(emailEdit.getText().toString()); // to be removed

        AuthRepository.getInstance().signUp(this, email, password);
    } /*end signUp*/

    private boolean isEmailValid(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && !email.isEmpty();
    } /*end isEmailValid*/

    private boolean isPasswordValid(String password){
        return !password.isEmpty() && password.length() > 5;
    }

    private boolean isFormValid(TextInputEditText emailEdit, TextInputEditText passwordEdit){
        /*checks if email and password is valid */

        boolean valid = true;
        String email = emailEdit.getText().toString();
        Log.d("cata", "email is : " + email);

        TextInputLayout emailLayout = findViewById(R.id.id_edit_email_layout);

        if( TextUtils.isEmpty(email) ){
            emailEdit.requestFocus();
//            emailEdit.setError("Required.");
            emailLayout.setError("Required.");
            valid = false;
        }

        if( !isEmailValid(email) ){
            emailLayout.setError("Invalid email");
            valid = false;
        }else {
            emailEdit.setError(null);
        }

        String password = passwordEdit.getText().toString();
        TextInputLayout passwordLayout = findViewById(R.id.id_edit_password_layout);
        if( TextUtils.isEmpty(password)){
            passwordEdit.requestFocus();
//            passwordEdit.setError("Required.");
            passwordLayout.setError("Required.");
            valid = false;
        }

        if(!isPasswordValid(password)){
            passwordEdit.requestFocus();
//            passwordEdit.setError("password min of 5 letters");
            passwordLayout.setError("password minimum of 5 letters");

            valid = false;
        }
        else {
            passwordEdit.setError(null);
        }

        return valid;

    } /*end isFormvalid*/

}
