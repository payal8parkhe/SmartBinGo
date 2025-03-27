package com.example.smartbingo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private EditText email, password;
    private Button loginBtn;
    private TextView signupLink, forgotPassword;
    private FirebaseAuth auth;
    private String userRole;  // Store role received from RoleSelectionActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);
        signupLink = findViewById(R.id.signupLink);
        forgotPassword = findViewById(R.id.forgotPassword);

        // Get role from intent
        userRole = getIntent().getStringExtra("ROLE");

        // Debugging Log
        Log.d("ROLE_DEBUG", "Received Role: " + userRole);

        // Hide signup link for Admin and Driver
        if ("admin".equalsIgnoreCase(userRole) || "driver".equalsIgnoreCase(userRole)) {
            Log.d("ROLE_DEBUG", "Hiding signup link");
            signupLink.setVisibility(TextView.GONE);  // Hide sign-up link
        } else {
            Log.d("ROLE_DEBUG", "Showing signup link");
            signupLink.setVisibility(TextView.VISIBLE); // Show sign-up link for users
        }

        loginBtn.setOnClickListener(v -> {
            String emailTxt = email.getText().toString().trim();
            String passwordTxt = password.getText().toString().trim();

            if (emailTxt.isEmpty() || passwordTxt.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Enter email and password", Toast.LENGTH_SHORT).show();
                return;
            }

            auth.signInWithEmailAndPassword(emailTxt, passwordTxt)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser user = auth.getCurrentUser();
                            if (user != null) {
                                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this, BottomNavigationActivity.class));
                                finish();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "Login Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        signupLink.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, SignupActivity.class));
        });

        forgotPassword.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, ForgotPassword.class));
        });
    }
}
