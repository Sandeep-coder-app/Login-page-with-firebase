package com.example.loginpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginpage.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SingUpActivity extends AppCompatActivity {
 EditText usertext,mobilext,emailtext,passtext1;
 Button signtxt;

 FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        mAuth = FirebaseAuth.getInstance();

        usertext = findViewById(R.id.usertext);
        mobilext = findViewById(R.id.mobiletext);
        emailtext = findViewById(R.id.emailtext);
        passtext1 = findViewById(R.id.passtext1);
        signtxt = findViewById(R.id.signtxt);

        signtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerNewUser();
            }
        });
    }
    private void registerNewUser()
    {
        String email, password;
        email = emailtext.getText().toString();
        password = passtext1.getText().toString();

        if(TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter email!",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),"Please enter password!!",Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Registration successful!",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(),"Registration failed!!"+" Please try again later",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}