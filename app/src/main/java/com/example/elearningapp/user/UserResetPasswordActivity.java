package com.example.elearningapp.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.elearningapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class UserResetPasswordActivity extends AppCompatActivity {
    EditText et_sendEmail;
    Button btn_reset;

    FirebaseAuth firebaseAuth;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_reset_password);

        et_sendEmail = findViewById(R.id.et_sendEmail);
        btn_reset = findViewById(R.id.btn_reset);

        firebaseAuth = FirebaseAuth.getInstance();

        btn_reset.setOnClickListener(v -> {
            String email = et_sendEmail.getText().toString();
            if (email.isEmpty()){
                Toast.makeText(UserResetPasswordActivity.this, "Email is empty", Toast.LENGTH_SHORT).show();
            }else{
                firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        Toast.makeText(UserResetPasswordActivity.this, "Please Check Your Email", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(UserResetPasswordActivity.this, UserLogin.class));
                    }else{
                        String error = Objects.requireNonNull(task.getException()).getMessage();
                        Toast.makeText(UserResetPasswordActivity.this, error, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}