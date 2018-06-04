package com.example.ximena.lextec;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //defining view objects
    private EditText editTextUser;
    private EditText editTextPassword;
    private Button buttonLogIn;

    //defining firebaseauth object
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUser = findViewById(R.id.editTextUser);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogIn = findViewById(R.id.buttonRegister);
        buttonLogIn.setOnClickListener(this);
        //initializing firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();
    }

    //method for user login
    private void userLogin() {
        String email = editTextUser.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        boolean cancel = false;

        editTextUser.setError(null);
        editTextPassword.setError(null);

        //checking if email and passwords are empty
        if (TextUtils.isEmpty(email)) {
            editTextUser.setError("Please enter a valid and not empty email");
            cancel = true;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Please enter a password");
            cancel = true;
        }

        if (cancel) {
            return;
        }

        //logging in the user
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        //start the profile activity
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),
                                        e.getMessage(),
                                        Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonRegister) {
            userLogin();
        }
    }
}
