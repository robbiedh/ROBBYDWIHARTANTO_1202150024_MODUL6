package com.example.bangijan69.robbydwih_1202150024_modul6;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import java.util.HashMap;

public class DaftarActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText editText_email,editText_password,editText_Repassword;
    private ProgressDialog mProgress;

    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        editText_email = (EditText)findViewById(R.id.email_daftar);
        editText_password = (EditText)findViewById(R.id.password_daftar);
        editText_Repassword = (EditText)findViewById(R.id.repassword_daftar);
        mAuth = FirebaseAuth.getInstance();
    }


    public void masuk_daftar(View view) {
    }

    public void daftar_daftar(View view) {
     String email = editText_email.getText().toString();
     String password =editText_password.getText().toString();
     String  repassword = editText_Repassword.getText().toString();
        if(email.isEmpty()|| password.isEmpty()||repassword.isEmpty()){
            Toast.makeText(DaftarActivity.this, "ada belum mengisi ",Toast.LENGTH_SHORT).show();
        }if (!password.equals(repassword)){
            Toast.makeText(DaftarActivity.this, "password tidak sama ",Toast.LENGTH_SHORT).show();


        }else {
            register_user(email,password);

        }


    }

    private void register_user( String email, String password) {


        //create user
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(DaftarActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(DaftarActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(DaftarActivity.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(DaftarActivity.this, "benar " + task.getException(),
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }



}
