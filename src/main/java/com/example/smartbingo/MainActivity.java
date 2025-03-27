package com.example.smartbingo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // Ensure you have a splash screen UI

        auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();

        // Show splash screen for 2 seconds, then redirect
        new Handler().postDelayed(() -> {
            if (currentUser != null) {
                // User logged in → Go to BottomNavigationActivity
                startActivity(new Intent(MainActivity.this, BottomNavigationActivity.class));
            } else {
                // User not logged in → Go to Role Selection
                startActivity(new Intent(MainActivity.this, RoleSelectionActivity.class));
            }
            finish(); // Close MainActivity
        }, 2000); // 2-second delay
    }
}
