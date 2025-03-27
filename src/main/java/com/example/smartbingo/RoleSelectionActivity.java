package com.example.smartbingo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class RoleSelectionActivity extends AppCompatActivity {

    private Button btnUser, btnAdmin, btnDriver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_selection);

        btnUser = findViewById(R.id.btnUser);
        btnAdmin = findViewById(R.id.btnAdmin);
        btnDriver = findViewById(R.id.btnDriver);

        btnUser.setOnClickListener(v -> navigateToLogin("user"));
        btnAdmin.setOnClickListener(v -> navigateToLogin("admin"));
        btnDriver.setOnClickListener(v -> navigateToLogin("driver"));
    }

    private void navigateToLogin(String role) {
        Intent intent = new Intent(RoleSelectionActivity.this, LoginActivity.class);
        intent.putExtra("ROLE", role); // Send role to LoginActivity
        startActivity(intent);
        finish();
    }
}
