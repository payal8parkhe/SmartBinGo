package com.example.smartbingo;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Map;

public class FeedbackFragment extends Fragment {
    private EditText editTextFeedback;
    private RatingBar ratingBar;
    private Button submitButton;
    private DatabaseReference feedbackRef;

    public FeedbackFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_feedback, container, false);

        editTextFeedback = rootView.findViewById(R.id.editTextFeedback);
        ratingBar = rootView.findViewById(R.id.ratingBar);  // Check if this ID is correct
        submitButton = rootView.findViewById(R.id.buttonSubmit);

        // Initialize Firebase Database
        feedbackRef = FirebaseDatabase.getInstance().getReference("feedback");

        // Submit Button Click Listener
        submitButton.setOnClickListener(v -> submitFeedback());

        return rootView;
    }

    private void submitFeedback() {
        String feedbackText = editTextFeedback.getText().toString().trim();
        float ratingValue = ratingBar.getRating();

        if (TextUtils.isEmpty(feedbackText)) {
            editTextFeedback.setError("Please enter your feedback");
            return;
        }

        // Create a unique feedback ID in Firebase
        String feedbackId = feedbackRef.push().getKey();

        // Store feedback in Firebase
        Map<String, Object> feedbackData = new HashMap<>();
        feedbackData.put("feedback", feedbackText);
        feedbackData.put("rating", ratingValue);

        if (feedbackId != null) {
            feedbackRef.child(feedbackId).setValue(feedbackData)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(getContext(), "Feedback Submitted!", Toast.LENGTH_SHORT).show();
                        editTextFeedback.setText(""); // Clear input
                        ratingBar.setRating(0); // Reset rating
                    })
                    .addOnFailureListener(e -> Toast.makeText(getContext(), "Failed to submit feedback!", Toast.LENGTH_SHORT).show());
        }
    }
}
