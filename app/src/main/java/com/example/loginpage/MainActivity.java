package com.example.loginpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
EditText usertext,passtext;
Button signtext;
TextView regtxt,forgettext;
FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        usertext = findViewById(R.id.usertext);
        passtext = findViewById(R.id.passtext);
        signtext = findViewById(R.id.signtext);
        regtxt = findViewById(R.id.regtxt);
        forgettext = findViewById(R.id.forgettext);

        signtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUserAccount();
            }
        });

        regtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s = new Intent(getApplicationContext(),SingUpActivity.class);
                startActivity(s);
            }
        });

        forgettext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forget();
            }
        });
    }

    private void forget()
    {
        String email = usertext.getText().toString().trim();
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Done sent",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Error Occurred",Toast.LENGTH_LONG).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loginUserAccount()
    {
        String email,password;
        email = usertext.getText().toString();
        password = passtext.getText().toString();

        if(TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),"Please enter email!!",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),"Please enter password!!",Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Login Successful!!",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Login failed!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}