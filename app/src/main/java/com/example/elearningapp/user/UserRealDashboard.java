package com.example.elearningapp.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.elearningapp.R;

public class UserRealDashboard extends AppCompatActivity {

    CardView cv_web, cv_frontend, cv_backend, cv_database, cv_android, cv_machineLearning;
    final String[] courses = {"Web", "Frontend", "Backend", "Database", "Android", "Machine Learning"};

    public static final String CATEGORY = "category";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_real_dashboard);

        cv_web = findViewById(R.id.cv_web);
        cv_frontend = findViewById(R.id.cv_frontend);
        cv_backend = findViewById(R.id.cv_backend);
        cv_database = findViewById(R.id.cv_database);
        cv_android = findViewById(R.id.cv_android);
        cv_machineLearning = findViewById(R.id.cv_machineLearning);

        cv_web.setOnClickListener(v -> startActivity(new Intent(UserRealDashboard.this, UserDashboard.class).putExtra(CATEGORY, courses[0])));
        cv_frontend.setOnClickListener(v -> startActivity(new Intent(UserRealDashboard.this, UserDashboard.class).putExtra(CATEGORY, courses[1])));
        cv_backend.setOnClickListener(v -> startActivity(new Intent(UserRealDashboard.this, UserDashboard.class).putExtra(CATEGORY, courses[2])));
        cv_database.setOnClickListener(v -> startActivity(new Intent(UserRealDashboard.this, UserDashboard.class).putExtra(CATEGORY, courses[3])));
        cv_android.setOnClickListener(v -> startActivity(new Intent(UserRealDashboard.this, UserDashboard.class).putExtra(CATEGORY, courses[4])));
        cv_machineLearning.setOnClickListener(v -> startActivity(new Intent(UserRealDashboard.this, UserDashboard.class).putExtra(CATEGORY, courses[5])));

    }
}