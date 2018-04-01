package com.example.bangijan69.robbydwih_1202150024_modul6;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    EditText email_edittext, password_editetxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        email_edittext=(EditText)findViewById(R.id.email_login);
        password_editetxt = (EditText)findViewById(R.id.password_login);


        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override

            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() != null) {

                    startActivity(new Intent(LoginActivity.this, BerandaActivity.class) );
                    finish();
                }

            }

        };
    }
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    void startSing(){
        String email = email_edittext.getText().toString();
        String password = password_editetxt.getText().toString();


        if (TextUtils.isEmpty(email) | TextUtils.isEmpty(password)) {

            Toast.makeText(LoginActivity.this, "Fields are Empty", Toast.LENGTH_SHORT).show();

        } else {



            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener() {

                @Override

                public void onComplete(@NonNull Task task) {



                    if (!task.isSuccessful()) {

                        Toast.makeText(LoginActivity.this, "Login Problem", Toast.LENGTH_SHORT).show();

                    }



                }

            });

        }

    }


    public void masuk(View view) {
        startSing();
    }

    public void daftar(View view) {
        Intent i = new Intent(LoginActivity.this,DaftarActivity.class);
        startActivity(i);
    }
}
