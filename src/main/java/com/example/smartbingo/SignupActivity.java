package com.example.smartbingo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {
    private EditText email, password, name, mobile, address, city;
    private Button signupBtn;
    private FirebaseAuth auth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        name = findViewById(R.id.name);
        mobile = findViewById(R.id.mobile);
        address = findViewById(R.id.address);
        city = findViewById(R.id.city);
        signupBtn = findViewById(R.id.signupBtn);

        signupBtn.setOnClickListener(v -> {
            String emailTxt = email.getText().toString().trim();
            String passwordTxt = password.getText().toString().trim();
            String nameTxt = name.getText().toString().trim();
            String mobileTxt = mobile.getText().toString().trim();
            String addressTxt = address.getText().toString().trim();
            String cityTxt = city.getText().toString().trim();

            if (emailTxt.isEmpty() || passwordTxt.isEmpty() || nameTxt.isEmpty() || mobileTxt.isEmpty() || addressTxt.isEmpty() || cityTxt.isEmpty()) {
                Toast.makeText(SignupActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                return;
            }

            auth.createUserWithEmailAndPassword(emailTxt, passwordTxt)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            String userId = auth.getCurrentUser().getUid();
                            Map<String, Object> user = new HashMap<>();
                            user.put("name", nameTxt);
                            user.put("email", emailTxt);
                            user.put("mobile", mobileTxt);
                            user.put("address", addressTxt);
                            user.put("city", cityTxt);
                            user.put("role", "User");

                            db.collection("users").document(userId).set(user)
                                    .addOnSuccessListener(aVoid -> {
                                        Toast.makeText(SignupActivity.this, "Signup Successful!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(SignupActivity.this, UserDashboardActivity.class));
                                        finish();
                                    })
                                    .addOnFailureListener(e ->
                                            Toast.makeText(SignupActivity.this, "Signup Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                                    );
                        } else {
                            Toast.makeText(SignupActivity.this, "Signup Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }
}
