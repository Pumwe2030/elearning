package com.example.elearningapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.elearningapp.admin.AdminLogin;
import com.example.elearningapp.tutor.TutorLogin;
import com.example.elearningapp.user.UserLogin;

public class MainActivity extends AppCompatActivity {

    Button btn_admin;
    Button btn_tutor;
    Button btn_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_admin = findViewById(R.id.btn_admin);
        btn_tutor = findViewById(R.id.btn_tutor);
        btn_user = findViewById(R.id.btn_user);

        btn_admin.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AdminLogin.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK)));
        btn_tutor.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, TutorLogin.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK)));
        btn_user.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, UserLogin.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK)));

    }
}