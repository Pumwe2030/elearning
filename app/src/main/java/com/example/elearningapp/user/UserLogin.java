package com.example.elearningapp.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elearningapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class UserLogin extends AppCompatActivity {

    EditText et_email, et_password;
    Button btn_login;
    TextView tv_registerBtn;

    TextView tv_forgotPassword;

    final String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$";

    final Pattern pat = Pattern.compile(emailRegex);

    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    /** @noinspection deprecation*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);
        tv_registerBtn = findViewById(R.id.tv_registerButton);
        tv_forgotPassword = findViewById(R.id.tv_forgotPassword);

        tv_forgotPassword.setOnClickListener(v -> startActivity(new Intent(UserLogin.this, UserResetPasswordActivity.class)));

        //noinspection deprecation
        progressDialog = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        btn_login.setOnClickListener(v -> performLogin());

        tv_registerBtn.setOnClickListener(v -> startActivity(new Intent(UserLogin.this, UserRegister.class)));

    }

    /** @noinspection deprecation*/
    private void performLogin() {
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();

        if (email.isEmpty()) {
            et_email.setError("Please Enter Email");
        } else if (!pat.matcher(email).matches()) {
            et_email.setError("Please Enter a valid Email");
        } else if (password.isEmpty()) {
            et_password.setError("Please input Password");
        } else if (password.length() < 6) {
            et_password.setError("Password too short");
        } else {
            //noinspection deprecation
            progressDialog.setMessage("Login in to your Account....");
            progressDialog.setTitle("Loading");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    sendUserToMainActivity();
                    Toast.makeText(UserLogin.this, "Login Successful", Toast.LENGTH_SHORT).show();
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(UserLogin.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void sendUserToMainActivity() {
        Intent intent = new Intent(UserLogin.this, UserRealDashboard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

}