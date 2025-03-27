package com.example.smartbingo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class Feedback1Fragment extends Fragment {

    private RecyclerView recyclerView;
    private FeedbackAdapter adapter;
    private List<String> feedbackList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        feedbackList = new ArrayList<>();
        feedbackList.add("Great service!");
        feedbackList.add("Please improve timings.");
        feedbackList.add("Efficient and clean.");

        adapter = new FeedbackAdapter(feedbackList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
