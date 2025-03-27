package com.example.smartbingo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserDashboardActivity extends AppCompatActivity {

    private TextView welcomeText;
    private Button trackVehicleBtn, notificationsBtn, logoutBtn;
    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        welcomeText = findViewById(R.id.welcomeText);
        trackVehicleBtn = findViewById(R.id.trackVehicleBtn);
        notificationsBtn = findViewById(R.id.notificationsBtn);
        logoutBtn = findViewById(R.id.logoutBtn);

        if (user != null) {
            userId = user.getUid();
            DocumentReference docRef = db.collection("users").document(userId);
            docRef.get().addOnSuccessListener(documentSnapshot -> {
                if (documentSnapshot.exists()) {
                    String name = documentSnapshot.getString("name");
                    welcomeText.setText("Welcome, " + name + "!");
                }
            });
        }

//        trackVehicleBtn.setOnClickListener(v -> startActivity(new Intent(UserDashboardActivity.this, TrackVehicleActivity.class)));
//
//        notificationsBtn.setOnClickListener(v -> startActivity(new Intent(UserDashboardActivity.this, NotificationsActivity.class)));

        logoutBtn.setOnClickListener(v -> {
            auth.signOut();
            Toast.makeText(UserDashboardActivity.this, "Logged out successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(UserDashboardActivity.this, LoginActivity.class));
            finish();
        });
    }
}
